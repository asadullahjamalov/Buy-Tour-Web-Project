package com.example.buytourwebproject.services;

import com.example.buytourwebproject.DTOs.AgentDTO;
import com.example.buytourwebproject.config.security.TokenUtil;
import com.example.buytourwebproject.exceptions.AgentNotFoundException;
import com.example.buytourwebproject.exceptions.PasswordsDoNotMatchException;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.ChangePassword;
import com.example.buytourwebproject.repositories.AgentRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AgentService {

    private final AgentRepo agentRepo;
    private final TokenUtil tokenUtil;
    private final AgentConfirmService agentConfirmService;

    public AgentService(AgentRepo agentRepo, TokenUtil tokenUtil,
                        AgentConfirmService agentConfirmService) {
        this.agentRepo = agentRepo;
        this.tokenUtil = tokenUtil;
        this.agentConfirmService = agentConfirmService;
    }


    public AgentDTO addAgent(Agent newAgent) {
        Agent findClient = agentRepo.getAgentByEmail(newAgent.getEmail());
        if (findClient != null) {
            throw new AgentNotFoundException("Not found", "404");
        }

        String encPassword = new BCryptPasswordEncoder().encode(newAgent.getPassword());
        newAgent.setPassword(encPassword);
        newAgent.setCreatedDate(LocalDate.now());
        Agent agent = agentRepo.save(newAgent);
        AgentDTO agentDTO = convertModelToDTO(agent);
        agentConfirmService.createToken(agent);
        return agentDTO;
    }

    public void changePassword(ChangePassword changePassword, String token) {
        if (changePassword.getNewPassword().equals(changePassword.getNewPasswordValidator())) {
            String encPassword = new BCryptPasswordEncoder().encode(changePassword.getNewPassword());
            agentRepo.changePasswordByEmail(encPassword, tokenUtil.getEmailFromToken(token));
        } else {
            throw new PasswordsDoNotMatchException("Passwords do not match", "400");
        }
    }

    public AgentDTO convertModelToDTO(Agent agent) {
        AgentDTO agentDTO = AgentDTO.builder()
                .agentName(agent.getAgentName())
                .agentSurname(agent.getAgentSurname())
                .agencyName(agent.getAgencyName())
                .TIN(agent.getTIN())
                .createdDate(agent.getCreatedDate())
                .email(agent.getEmail())
                .build();
        return agentDTO;
    }

    public boolean getAgentStatus(String email){
        return agentRepo.getAgentStatusByEmail(email);
    }
}
