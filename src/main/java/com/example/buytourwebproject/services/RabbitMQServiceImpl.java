package com.example.buytourwebproject.services;


import com.example.buytourwebproject.DTOs.AcceptQueueDTO;
import com.example.buytourwebproject.DTOs.OfferQueueDTO;
import com.example.buytourwebproject.DTOs.RequestQueueDTO;
import com.example.buytourwebproject.DTOs.StopQueueDTO;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import com.example.buytourwebproject.repositories.AcceptedOfferResponseRepo;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.repositories.RequestRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import com.example.buytourwebproject.services.interfaces.RabbitMQService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    private final RabbitTemplate rabbitTemplate;
    private final RequestRepo requestRepo;
    private final RequestStatusRepo requestStatusRepo;
    private final AgentRepo agentRepo;
    private final AcceptedOfferResponseRepo acceptedOfferResponseRepo;

    public RabbitMQServiceImpl(RabbitTemplate rabbitTemplate, RequestRepo requestRepo,
                               RequestStatusRepo requestStatusRepo, AgentRepo agentRepo,
                               AcceptedOfferResponseRepo acceptedOfferResponseRepo) {
        this.rabbitTemplate = rabbitTemplate;
        this.requestRepo = requestRepo;
        this.requestStatusRepo = requestStatusRepo;
        this.agentRepo = agentRepo;
        this.acceptedOfferResponseRepo = acceptedOfferResponseRepo;
    }

    @Override
    public void send(OfferQueueDTO offerQueueDTO) {
        rabbitTemplate.convertAndSend("buy_tour_web_exchange",
                "buy_tour_web_routing_key", offerQueueDTO);
    }


    @RabbitListener(queues = "request_queue")
    public void requestListener(RequestQueueDTO requestQueueDTO) {
        System.out.println(requestQueueDTO.toString());

        Request request = Request.builder().uuid(requestQueueDTO.getUuid())
                .jsonAnswers(requestQueueDTO.getJsonAnswers())
                .createdDate(LocalDateTime.now())
                .expireDate(LocalDateTime.now().plusHours(8))
                .isExpired(false)
                .build();

        requestRepo.save(request);

        for (Agent agent : agentRepo.getAllAgents()) {
            requestStatusRepo.save(RequestStatus.builder().request(request)
                    .requestType(RequestType.NEW)
                    .agent(agent).build());

        }

    }

    @RabbitListener(queues = "stop_queue")
    public void stopEventListener(StopQueueDTO stopQueueDTO) {
        requestRepo.deactivateRequestByUuid(stopQueueDTO.getUuid());
        System.out.println("Session was deactivated");
    }


    @RabbitListener(queues = "accept_queue")
    public void acceptEventListener(AcceptQueueDTO acceptQueueDTO) {
        requestStatusRepo.changeRequestStatusTypeByAgentIdAndRequestId(RequestType.ACCEPTED,
                acceptQueueDTO.getAgentId(), requestRepo.getRequestByUuid(acceptQueueDTO.getUuid()).getId());
        acceptedOfferResponseRepo.save(AcceptedOfferResponse.builder()
                .agentId(acceptQueueDTO.getAgentId())
                .uuid(acceptQueueDTO.getUuid())
                .info(acceptQueueDTO.getInfo())
                .build());
        System.out.println("Request was accepted");
    }

}




