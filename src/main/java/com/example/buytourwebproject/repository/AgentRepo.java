package com.example.buytourwebproject.repository;

import com.example.buytourwebproject.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgentRepo extends JpaRepository<Agent, Long> {

    Agent getAgentById(long agentId);

    @Query(value = "select a from Agent a where a.email=:email")
    Agent getAgentByEmail(String email);

    @Query(value = "select a.isActive from Agent a where a.email=:email")
    boolean getAgentStatusByEmail(String email);

    @Query(value = "select a from Agent a")
    List<Agent> getAllAgents();

    @Modifying
    @Query("update Agent a set a.password=:password where a.email= :email")
    void changePasswordByEmail(String password, String email);

    @Modifying
    @Query("update Agent a set a.isActive=true where a.email= :email")
    void changeIsActiveByEmail(String email);

}