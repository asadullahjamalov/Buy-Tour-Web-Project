package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.AgentDTO;
import com.example.buytourwebproject.config.security.JwtTokenUtil;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.jwt.JwtRequest;
import com.example.buytourwebproject.models.jwt.JwtResponse;
import com.example.buytourwebproject.services.AgentService;
import com.example.buytourwebproject.services.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/agent/")
public class AgentController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final AgentService agentService;

    public AgentController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                           JwtUserDetailsService userDetailsService, AgentService agentService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.agentService = agentService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("register")
    public ResponseEntity<AgentDTO> addClient(@RequestBody Agent agent){
        AgentDTO agentDTO =  agentService.addAgent(agent);
        return new ResponseEntity<>(agentDTO, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

