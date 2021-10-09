package com.example.buytourwebproject.service;

import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.AgentConfirmToken;
import com.example.buytourwebproject.repository.AgentConfirmTokenRepo;
import com.example.buytourwebproject.repository.AgentRepo;
import com.example.buytourwebproject.util.MessageUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AgentConfirmService {

    private final AgentConfirmTokenRepo agentConfirmTokenRepo;
    private final AgentRepo agentRepo;
    private final MessageUtil messageUtil;

    public AgentConfirmService(AgentConfirmTokenRepo agentConfirmTokenRepo,
                               AgentRepo agentRepo, MessageUtil messageUtil) {
        this.agentConfirmTokenRepo = agentConfirmTokenRepo;
        this.agentRepo = agentRepo;
        this.messageUtil = messageUtil;
    }

    public void createToken(Agent agent) {
        String token = agentConfirmTokenRepo
                .save(AgentConfirmToken.builder()
                        .agent(agent)
                        .confirmationToken(UUID.randomUUID().toString())
                        .createdDate(LocalDateTime.now())
                        .build())
                .getConfirmationToken();
        messageUtil.regVerifyNotification(agent, token);
    }


    public void verifyToken(String token) {
        AgentConfirmToken dbToken = agentConfirmTokenRepo.getAgentConfirmTokenByConfirmationToken(token);
        Agent agent = agentRepo.getAgentByEmail(dbToken.getAgent().getEmail());
        agentRepo.changeIsActiveByEmail(dbToken.getAgent().getEmail());
        agentConfirmTokenRepo.delete(dbToken);
        messageUtil.successRegister(agent);
    }

}
