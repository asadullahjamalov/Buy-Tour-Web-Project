package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.config.security.JwtTokenUtil;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.exceptions.OfferWasAlreadySentException;
import com.example.buytourwebproject.exceptions.RequestExpiredException;
import com.example.buytourwebproject.exceptions.RequestNotFoundException;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.repositories.RequestRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import com.example.buytourwebproject.services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/offer/")
public class OfferController {

    private final OfferService offerService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AgentRepo agentRepo;
    private final RequestRepo requestRepo;
    private final RequestStatusRepo requestStatusRepo;

    public OfferController(OfferService offerService, JwtTokenUtil jwtTokenUtil,
                           AgentRepo agentRepo, RequestRepo requestRepo,
                           RequestStatusRepo requestStatusRepo) {
        this.offerService = offerService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.agentRepo = agentRepo;
        this.requestRepo = requestRepo;
        this.requestStatusRepo = requestStatusRepo;
    }

    @PostMapping("create/{id}")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody Offer offer,
                                                @PathVariable Long id,
                                                @RequestHeader("Authorization") String token) throws IOException {
        offer.setAgent(agentRepo.getAgentById(jwtTokenUtil.getUserId(token)));
        System.out.println("Auth passed");
        if (requestRepo.getRequestById(id) == null) {
            throw new RequestNotFoundException("Request Not Found", "404");
        } else if (!(requestStatusRepo.getRequestStatusByRequestAndAgent(requestRepo.getRequestById(id), offer.getAgent())
                .getRequestType().equals(RequestType.NEW))) {
            throw new OfferWasAlreadySentException("Offer was already sent", "400");
        }else if(requestRepo.getRequestById(id).getIsExpired()){
            throw new RequestExpiredException("Request was expired", "400");
        }
        else {
            offer.setRequest(requestRepo.getRequestById(id));
            offerService.createOffer(offer);
            OfferDTO offerDTO = offerService.convertModelToDTO(offer);
            return new ResponseEntity<>(offerDTO, HttpStatus.OK);
        }

    }


}
