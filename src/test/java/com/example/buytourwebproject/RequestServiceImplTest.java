package com.example.buytourwebproject;

import com.example.buytourwebproject.config.security.TokenUtil;
import com.example.buytourwebproject.enums.ArchiveStatus;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.repository.AcceptedOfferResponseRepo;
import com.example.buytourwebproject.repository.AgentRepo;
import com.example.buytourwebproject.repository.RequestRepo;
import com.example.buytourwebproject.repository.RequestStatusRepo;
import com.example.buytourwebproject.service.RequestServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class RequestServiceImplTest {
    @Mock
    private RequestStatusRepo requestStatusRepo;
    @Mock
    private TokenUtil tokenUtil;
    @Mock
    private AgentRepo agentRepo;
    @Mock
    private AcceptedOfferResponseRepo acceptedOfferResponseRepo;
    @Mock
    private RequestRepo requestRepo;

    private AutoCloseable autoCloseable;
    private RequestServiceImpl underTest;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RequestServiceImpl(requestStatusRepo, tokenUtil, agentRepo,
                acceptedOfferResponseRepo, requestRepo);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getOfferedRequests() {
        String token = new String();
        underTest.getOfferedRequests(token);
        Long id = verify(tokenUtil).getAgentId(token);
        Agent agent = verify(agentRepo).getAgentById(id);
        verify(requestStatusRepo).getRequestsByTypeAndAgent(RequestType.OFFERED, agent);
    }

    @Test
    void getArchivedRequestsTest() {
        String token = new String();
        underTest.getArchivedRequests(token);
        Long id = verify(tokenUtil).getAgentId(token);
        Agent agent = verify(agentRepo).getAgentById(id);
        verify(requestStatusRepo).getRequestsByArchiveStatusAndAgent(ArchiveStatus.ARCHIVED, agent);
    }

    @Test
    void getUnarchivedRequestsTest() {
        String token = new String();
        underTest.getUnarchivedRequests(token);
        Long id = verify(tokenUtil).getAgentId(token);
        Agent agent = verify(agentRepo).getAgentById(id);
        verify(requestStatusRepo).getRequestsByArchiveStatusAndAgent(ArchiveStatus.NOT_ARCHIVED, agent);
    }

    @Test
    void getAcceptedRequests() {
        String token = new String();
        underTest.getAcceptedRequests(token);
        Long id = verify(tokenUtil).getAgentId(token);
        Agent agent = verify(agentRepo).getAgentById(id);
        verify(requestStatusRepo).getRequestsByTypeAndAgent(RequestType.ACCEPTED, agent);
    }


    @Test
    void setRequestArchived() {
        String token = new String();
        Long requestId = null;
        underTest.setRequestArchived(token, requestId);
        Long id = verify(tokenUtil).getAgentId(token);
        Agent agent = verify(agentRepo).getAgentById(id);
        verify(requestStatusRepo).changeRequestArchiveStatusByAgentAndRequestId(ArchiveStatus.ARCHIVED, agent, requestId);
    }

    @Test
    void getAcceptedRequestInfo() {
        String token = new String();
        Long requestId = null;
        underTest.getAcceptedRequestInfo(token, requestId);
        Long id = verify(tokenUtil).getAgentId(token);
        String uuid = verify(requestRepo).getUuidByRequestId(requestId);
        verify(acceptedOfferResponseRepo).getAcceptedOfferResponseByAgentIdAndUuid(id, uuid);
    }

    @Test
    void getAcceptedRequestInfoAll() {
        String token = new String();
        underTest.getAcceptedRequestInfoAll(token);
        Long id = verify(tokenUtil).getAgentId(token);
        verify(acceptedOfferResponseRepo).getAcceptedOfferResponseByAgentId(id);
    }
}