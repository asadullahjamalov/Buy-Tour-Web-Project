package com.example.buytourwebproject.repositories;


import com.example.buytourwebproject.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgentRepo extends JpaRepository<Agent, Long> {

    Agent getAgentById(long agentId);

    @Query(value = "select a from Agent a where a.email=:email")
    Agent getAgentByEmail(String email);

}