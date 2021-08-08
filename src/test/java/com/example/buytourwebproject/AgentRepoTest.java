package com.example.buytourwebproject;

import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.repositories.AgentRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.io.AbstractFileResolvingResource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AgentRepoTest {

    @Autowired
    private AgentRepo underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void getAgentById() {
        Agent agent1 = new Agent();
        underTest.save(agent1);

        Agent agent = underTest.getAgentById(agent1.getId());

        assertThat(agent).isEqualTo(agent1);
    }

    @Test
    void getAgentByEmail() {
        Agent agent1 = Agent.builder().email("test@test.com").build();
        underTest.save(agent1);

        Agent agent = underTest.getAgentByEmail("test@test.com");

        assertThat(agent).isEqualTo(agent1);
    }

    @Test
    void getAllAgents() {
        Agent agent1 = new Agent();
        underTest.save(agent1);

        Agent agent2 = new Agent();
        underTest.save(agent2);

        Agent agent3 = new Agent();
        underTest.save(agent3);

        List<Agent> agentList = underTest.getAllAgents();

        assertThat(agentList).isEqualTo(Arrays.asList(agent1, agent2, agent3));
    }

}