package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.config.security.JwtTokenUtil;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Request;
import com.example.buytourwebproject.models.RequestStatus;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.services.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("api/request/")
public class RequestController {
    private RequestService requestService;
    private JwtTokenUtil jwtTokenUtil;
    private AgentRepo agentRepo;


    public RequestController(RequestService requestService, JwtTokenUtil jwtTokenUtil, AgentRepo agentRepo) {
        this.requestService = requestService;
        this.agentRepo = agentRepo;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @GetMapping("get-archived")
    public ResponseEntity<List<Request>> getArchivedRequests(@RequestHeader("Authorization") String token) {
        Agent agent = agentRepo.getAgentById(jwtTokenUtil.getUserId(token));
        return new ResponseEntity<>(requestService.getArchivedRequests(agent), HttpStatus.OK);
    }

    @GetMapping("get-offered")
    public ResponseEntity<List<Request>> getOfferedRequests(@RequestHeader("Authorization") String token) {
        Agent agent = agentRepo.getAgentById(jwtTokenUtil.getUserId(token));
        return new ResponseEntity<>(requestService.getOfferedRequests(agent), HttpStatus.OK);
    }

    @PutMapping("set-archived/{id}")
    public ResponseEntity<String> setArchived(@RequestHeader("Authorization") String token,
                                              @PathVariable Long id) {
        Agent agent = agentRepo.getAgentById(jwtTokenUtil.getUserId(token));
        requestService.setRequestArchived(agent, id);
        return new ResponseEntity<>("Request was archived", HttpStatus.OK);
    }


    @GetMapping("get-accepted")
    public ResponseEntity<List<Request>> getAcceptedRequests(@RequestHeader("Authorization") String token) {
        Agent agent = agentRepo.getAgentById(jwtTokenUtil.getUserId(token));
        return new ResponseEntity<>(requestService.getAcceptedRequests(agent), HttpStatus.OK);
    }


}
