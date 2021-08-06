package com.example.buytourwebproject.services;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.DTOs.OfferQueueDTO;
import com.example.buytourwebproject.config.security.TokenUtil;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.exceptions.OfferWasAlreadySentException;
import com.example.buytourwebproject.exceptions.RequestExpiredException;
import com.example.buytourwebproject.exceptions.RequestNotFoundException;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.repositories.OfferRepo;
import com.example.buytourwebproject.repositories.RequestRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import com.example.buytourwebproject.services.interfaces.RabbitMQService;
import com.example.buytourwebproject.utils.PhotoConverterUtil;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class OfferService {

    private final OfferRepo offerRepo;
    private final PhotoConverterUtil photoConverterUtil;
    private final RabbitMQService rabbitMQService;
    private final RequestStatusRepo requestStatusRepo;
    private final RequestRepo requestRepo;
    private final AgentRepo agentRepo;
    private final TokenUtil tokenUtil;


    public OfferService(OfferRepo offerRepo, PhotoConverterUtil photoConverterUtil,
                        RabbitMQService rabbitMQService, RequestStatusRepo requestStatusRepo,
                        RequestRepo requestRepo, AgentRepo agentRepo, TokenUtil tokenUtil) {
        this.offerRepo = offerRepo;
        this.photoConverterUtil = photoConverterUtil;
        this.rabbitMQService = rabbitMQService;
        this.requestStatusRepo = requestStatusRepo;
        this.requestRepo = requestRepo;
        this.agentRepo = agentRepo;
        this.tokenUtil = tokenUtil;
    }

    public void createOffer(Offer offer, Long id, String token) throws IOException {

        offer.setAgent(agentRepo.getAgentById(tokenUtil.getAgentId(token)));
        System.out.println("Auth passed");
        System.out.println(requestRepo.getRequestById(id).getUuid());
        System.out.println();
        if (requestRepo.getRequestById(id) == null) {
            throw new RequestNotFoundException("Request Not Found", "404");
        } else if (!(requestStatusRepo.getRequestStatusByRequestAndAgent(requestRepo.getRequestById(id), offer.getAgent())
                .getRequestType().equals(RequestType.NEW))) {
            throw new OfferWasAlreadySentException("Offer was already sent", "400");
        } else if (requestRepo.getRequestById(id).getIsExpired()) {
            throw new RequestExpiredException("Request was expired", "400");
        } else {
            offer.setRequest(requestRepo.getRequestById(id));
            offerRepo.save(offer);
            BufferedImage image = photoConverterUtil.convertTextToImage(offer);
            OfferQueueDTO offerQueueDTO = new OfferQueueDTO();
            offerQueueDTO.setAgentId(offer.getAgent().getId());
            offerQueueDTO.setImage(photoConverterUtil.convertToByteArray(image));
            offerQueueDTO.setUuid(offer.getRequest().getUuid());
            requestStatusRepo.changeRequestStatusTypeByAgentAndRequestId(RequestType.OFFERED,
                    offer.getAgent(), offer.getRequest().getId());
            rabbitMQService.send(offerQueueDTO);
        }

    }


    public OfferDTO convertModelToDTO(Offer offer) {
        OfferDTO offerDTO = OfferDTO.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .travelLocations(offer.getTravelLocations())
                .price(offer.getPrice())
                .travelStartDate(offer.getTravelStartDate())
                .travelEndDate(offer.getTravelEndDate())
                .notes(offer.getNotes())
                .build();
        return offerDTO;
    }


}
