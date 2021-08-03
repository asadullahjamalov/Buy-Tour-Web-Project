package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.AgentDTO;
import com.example.buytourwebproject.config.security.JwtTokenUtil;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.ChangePassword;
import com.example.buytourwebproject.models.jwt.JwtRequest;
import com.example.buytourwebproject.models.jwt.JwtResponse;
import com.example.buytourwebproject.services.AgentConfirmService;
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
    private final AgentConfirmService agentConfirmService;

    public AgentController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil,
                           JwtUserDetailsService userDetailsService, AgentService agentService,
                           AgentConfirmService agentConfirmService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.agentService = agentService;
        this.agentConfirmService = agentConfirmService;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("register")
    public ResponseEntity<AgentDTO> addAgent(@RequestBody Agent agent) {
        AgentDTO agentDTO = agentService.addAgent(agent);
        return new ResponseEntity<>(agentDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<String> confirmUserAccount(@RequestParam("token") String confirmationToken) {
        agentConfirmService.verifyToken(confirmationToken);
        return new ResponseEntity( "You completed registration, successfully",  HttpStatus.OK);
    }

    @PostMapping("change-password")
    public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token,
                                                 @RequestBody ChangePassword changePassword) {
        agentService.changePassword(changePassword, token);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

