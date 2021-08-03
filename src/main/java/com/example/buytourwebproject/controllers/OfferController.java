package com.example.buytourwebproject.controllers;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.services.OfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/api/offer/")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("create/{id}")
    public ResponseEntity<OfferDTO> createOffer(@RequestBody Offer offer,
                                                @PathVariable Long id,
                                                @RequestHeader("Authorization") String token) throws IOException {
        offerService.createOffer(offer, id, token);
        OfferDTO offerDTO = offerService.convertModelToDTO(offer);
        return new ResponseEntity<>(offerDTO, HttpStatus.OK);

    }


}
