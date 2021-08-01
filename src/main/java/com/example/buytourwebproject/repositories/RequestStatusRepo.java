package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestStatusRepo extends JpaRepository<RequestStatus, Long> {

    @Query("select r.request from RequestStatus r where r.requestType= :type and r.agent= :agent")
    List<Request> getRequestsByTypeAndAgent(RequestType type, Agent agent);

    @Modifying
    @Transactional
    @Query("update RequestStatus r set r.requestType=:type where r.agent= :agent and r.request.id=:requestId")
    void changeRequestStatusTypeByAgentAndRequestId(RequestType type, Agent agent, Long requestId);

    @Modifying
    @Transactional
    @Query("update RequestStatus r set r.requestType=:type where r.agent.id= :agentId and r.request.id=:requestId")
    void changeRequestStatusTypeByAgentIdAndRequestId(RequestType type, Long agentId, Long requestId);

    @Query("select r from RequestStatus r where r.request= :request and r.agent= :agent")
    RequestStatus getRequestStatusByRequestAndAgent(Request request, Agent agent);
}
