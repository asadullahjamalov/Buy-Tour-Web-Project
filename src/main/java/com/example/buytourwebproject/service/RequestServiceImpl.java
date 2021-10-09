package com.example.buytourwebproject.service;

import com.example.buytourwebproject.config.security.TokenUtil;
import com.example.buytourwebproject.enums.ArchiveStatus;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.AcceptedOfferResponse;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.repository.AcceptedOfferResponseRepo;
import com.example.buytourwebproject.repository.AgentRepo;
import com.example.buytourwebproject.repository.RequestRepo;
import com.example.buytourwebproject.repository.RequestStatusRepo;
import com.example.buytourwebproject.service.interfaces.RequestService;
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
               tokenUtil.getAgentId(token), requestRepo.getUuidByRequestId(requestId));
    }

    @Override
    public List<AcceptedOfferResponse> getAcceptedRequestInfoAll(String token) {
        return acceptedOfferResponseRepo.getAcceptedOfferResponseByAgentId(tokenUtil.getAgentId(token));
    }

}
