package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.models.AgentConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentConfirmTokenRepo extends JpaRepository<AgentConfirmToken, Long> {

    AgentConfirmToken getAgentConfirmTokenByConfirmationToken(String confirmationToken);
}
