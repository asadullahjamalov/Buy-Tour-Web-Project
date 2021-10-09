package com.example.buytourwebproject;

import com.example.buytourwebproject.enums.ArchiveStatus;
import com.example.buytourwebproject.enums.RequestType;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import com.example.buytourwebproject.repository.AgentRepo;
import com.example.buytourwebproject.repository.RequestRepo;
import com.example.buytourwebproject.repository.RequestStatusRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class RequestStatusRepoTest {

    @Autowired
    private RequestStatusRepo underTest;

    @Autowired
    private AgentRepo agentRepo;

    @Autowired
    private RequestRepo requestRepo;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
        agentRepo.deleteAll();
    }

    @Test
    void getRequestsByTypeAndAgent() {
        Agent agent1 = new Agent();
        agentRepo.save(agent1);
        Request request1 = new Request();
        requestRepo.save(request1);
        RequestStatus requestStatus1 = RequestStatus.builder().requestType(RequestType.NEW)
                .agent(agent1).request(request1).build();
        underTest.save(requestStatus1);

        Request request2 = new Request();
        requestRepo.save(request2);
        RequestStatus requestStatus2 = RequestStatus.builder().requestType(RequestType.NEW)
                .agent(agent1).request(request2).build();
        underTest.save(requestStatus2);

        List<Request> requestList = underTest.getRequestsByTypeAndAgent(RequestType.NEW, agent1);

        assertThat(requestList).isEqualTo(Arrays.asList(request1, request2));
    }

    @Test
    void getRequestsByArchiveStatusAndAgent() {
        Agent agent1 = new Agent();
        agentRepo.save(agent1);
        Request request1 = new Request();
        requestRepo.save(request1);
        RequestStatus requestStatus1 = RequestStatus.builder().archiveStatus(ArchiveStatus.ARCHIVED)
                .agent(agent1).request(request1).build();
        underTest.save(requestStatus1);

        Request request2 = new Request();
        requestRepo.save(request2);
        RequestStatus requestStatus2 = RequestStatus.builder().archiveStatus(ArchiveStatus.ARCHIVED)
                .agent(agent1).request(request2).build();
        underTest.save(requestStatus2);

        List<Request> requestList = underTest.getRequestsByArchiveStatusAndAgent(ArchiveStatus.ARCHIVED, agent1);

        assertThat(requestList).isEqualTo(Arrays.asList(request1, request2));
    }

}