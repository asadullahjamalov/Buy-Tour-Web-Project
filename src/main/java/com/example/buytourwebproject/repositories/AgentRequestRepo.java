package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.models.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRequestRepo extends JpaRepository<RequestStatus, Long> {
}
