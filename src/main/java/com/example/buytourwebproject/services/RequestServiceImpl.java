package com.example.buytourwebproject.services;

import com.example.buytourwebproject.config.security.TokenUtil;
import com.example.buytourwebproject.enums.ArchiveStatus;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.repositories.AcceptedOfferResponseRepo;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.repositories.RequestRepo;
import com.example.buytourwebproject.repositories.RequestStatusRepo;
import com.example.buytourwebproject.services.interfaces.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestServiceImpl implements RequestService {

    private final RequestStatusRepo requestStatusRepo;
    private final TokenUtil tokenUtil;
    private final AgentRepo agentRepo;
    private final AcceptedOfferResponseRepo acceptedOfferResponseRepo;
    private final RequestRepo requestRepo;

    public RequestServiceImpl(RequestStatusRepo requestStatusRepo, TokenUtil tokenUtil,
                              AgentRepo agentRepo, AcceptedOfferResponseRepo acceptedOfferResponseRepo,
                              RequestRepo requestRepo) {
        this.requestStatusRepo = requestStatusRepo;
        this.tokenUtil = tokenUtil;
        this.agentRepo = agentRepo;
        this.acceptedOfferResponseRepo = acceptedOfferResponseRepo;
        this.requestRepo = requestRepo;
    }

    @Override
    public List<Request> getOfferedRequests(String token) {
        return requestStatusRepo.getRequestsByTypeAndAgent(RequestType.OFFERED, agentRepo.getAgentById(tokenUtil.getAgentId(token)));
    }

    @Override
    public List<Request> getArchivedRequests(String token) {
        return requestStatusRepo.getRequestsByArchiveStatusAndAgent(ArchiveStatus.ARCHIVED, agentRepo.getAgentById(tokenUtil.getAgentId(token)));
    }

    @Override
    public List<Request> getUnarchivedRequests(String token) {
        return requestStatusRepo.getRequestsByArchiveStatusAndAgent(ArchiveStatus.NOT_ARCHIVED, agentRepo.getAgentById(tokenUtil.getAgentId(token)));
    }

    @Override
    public List<Request> getAcceptedRequests(String token) {
        return requestStatusRepo.getRequestsByTypeAndAgent(RequestType.ACCEPTED, agentRepo.getAgentById(tokenUtil.getAgentId(token)));
    }

    @Override
    public void setRequestArchived(String token, Long requestId) {
        requestStatusRepo.changeRequestArchiveStatusByAgentAndRequestId(ArchiveStatus.ARCHIVED, agentRepo.getAgentById(tokenUtil.getAgentId(token)), requestId);
    }

    @Override
    public AcceptedOfferResponse getAcceptedRequestInfo(String token, Long requestId) {
        return acceptedOfferResponseRepo.getAcceptedOfferResponseByAgentIdAndUuid(
                agentRepo.getAgentById(tokenUtil.getAgentId(token)).getId(), requestRepo.getUuidByRequestId(requestId));
    }

    @Override
    public List<AcceptedOfferResponse> getAcceptedRequestInfoAll(String token) {
        return acceptedOfferResponseRepo.getAcceptedOfferResponseByAgentId(agentRepo.getAgentById(tokenUtil.getAgentId(token)).getId());
    }

}
