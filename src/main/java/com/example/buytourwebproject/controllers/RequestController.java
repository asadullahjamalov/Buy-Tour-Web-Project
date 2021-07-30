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
@RequiredArgsConstructor
public class RequestController {
    private RequestService requestService;
    private JwtTokenUtil jwtTokenUtil;
    private AgentRepo agentRepo;


    public RequestController(RequestService requestService, JwtTokenUtil jwtTokenUtil,AgentRepo agentRepo
    ) {
        this.requestService = requestService;
        this.agentRepo =agentRepo;
        this.jwtTokenUtil = jwtTokenUtil;
    }

//    @PostMapping("create")
//    public ResponseEntity<?> createRequest(){
//        return null;
////            return new ResponseEntity<>(categories, HttpStatus.OK);
//    }

    @GetMapping("get-archived")
    public ResponseEntity<?> getArchivedRequests(@RequestHeader("Authorization") String token){
        long agentId = jwtTokenUtil.getUserId(token);
        Agent agent = agentRepo.getAgentById(agentId);
        return new ResponseEntity<>(requestService.getArchivedRequests(agent), HttpStatus.OK);
    }

    @PutMapping("set-archived")
    public ResponseEntity<?> setArchived(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @GetMapping("get-offered")
    public ResponseEntity<List<Request>> getOfferedRequests(
            @RequestHeader("Authorization") String token){
        long agentId = jwtTokenUtil.getUserId(token);
        Agent agent = agentRepo.getAgentById(agentId);
        return new ResponseEntity<>(requestService.getOfferedRequests(agent), HttpStatus.OK);
    }

    @PutMapping("set-offered")
    public ResponseEntity<?> setOffered(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }

    @PutMapping("set-accepted")
    public ResponseEntity<?> setAccepted(){
        return null;
//            return new ResponseEntity<>(clientDTO,HttpStatus.OK);
    }


}
