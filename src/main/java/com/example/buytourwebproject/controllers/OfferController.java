package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.config.JwtTokenUtil;
import com.example.buytourwebproject.models.Agent;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.repositories.AgentRepo;
import com.example.buytourwebproject.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/offer/")
public class OfferController {

    private OfferService offerService;
    private JwtTokenUtil jwtTokenUtil;
    private AgentRepo agentRepo;

    public OfferController(OfferService offerService, JwtTokenUtil jwtTokenUtil, AgentRepo agentRepo) {
        this.offerService = offerService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.agentRepo = agentRepo;
    }

    @PostMapping("create/{id}")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody Offer offer,
                                        @RequestParam Long id,
                                        @RequestHeader("Authorization") String token) {
        System.out.println("auth passed");
//        System.out.println(">> "+httpServletRequest.getHeader("Authorization"));
        long agentId = jwtTokenUtil.getUserId(token);
        Agent agent = agentRepo.getAgentById(agentId);
        offer.setAgent(agent);
        offerService.createOffer(offer);
        OfferDTO offerDTO = offerService.convertModelToDTO(offer);
        return new ResponseEntity<>(offerDTO, HttpStatus.OK);

    }


}
