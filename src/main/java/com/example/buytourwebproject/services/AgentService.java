package com.example.buytourwebproject.services;

import com.example.buytourwebproject.DTOs.AgentDTO;
import com.example.buytourwebproject.exceptions.AgentNotFoundException;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.repositories.AgentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AgentService {

    private final AgentRepo agentRepo;


    public AgentDTO getAgent(long id) {
        Agent agent = agentRepo.getAgentById(id);
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


    public AgentDTO addAgent(Agent newAgent) {
        Agent findClient = agentRepo.getAgentByEmail(newAgent.getEmail());
        if (findClient!=null){
            throw new AgentNotFoundException("Not found", "404");
        }

        String encPassword = new BCryptPasswordEncoder().encode(newAgent.getPassword());
        newAgent.setPassword(encPassword);
        newAgent.setCreatedDate(LocalDate.now());
        Agent agent = agentRepo.save(newAgent);
        AgentDTO agentDTO = convertModelToDTO(agent);
        return agentDTO;
    }

    public AgentDTO convertModelToDTO(Agent agent){
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
}
