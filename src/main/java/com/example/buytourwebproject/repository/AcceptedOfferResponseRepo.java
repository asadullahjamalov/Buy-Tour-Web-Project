package com.example.buytourwebproject.repository;

import com.example.buytourwebproject.models.AcceptedOfferResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcceptedOfferResponseRepo extends JpaRepository<AcceptedOfferResponse, Long> {

    @Query(value = "select a from AcceptedOfferResponse a where a.agentId=:agentId and a.uuid=:uuid")
    AcceptedOfferResponse getAcceptedOfferResponseByAgentIdAndUuid(Long agentId, String uuid);

    @Query(value = "select a from AcceptedOfferResponse a where a.agentId=:agentId")
    List<AcceptedOfferResponse> getAcceptedOfferResponseByAgentId(Long agentId);
}
