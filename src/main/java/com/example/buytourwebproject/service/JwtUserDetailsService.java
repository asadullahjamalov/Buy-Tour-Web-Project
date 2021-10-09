package com.example.buytourwebproject.service;

import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.repository.AgentRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final AgentRepo agentRepo;

    public JwtUserDetailsService(AgentRepo agentRepo) {
        this.agentRepo = agentRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Agent agent = agentRepo.getAgentByEmail(email);
        System.out.println(agent.getEmail());
        if (agent != null) {
            return new User(email, agent.getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }
}
