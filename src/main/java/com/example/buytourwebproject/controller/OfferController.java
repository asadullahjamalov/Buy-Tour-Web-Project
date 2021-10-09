package com.example.buytourwebproject.controller;

import com.example.buytourwebproject.dto.OfferDTO;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
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
        log.info("Offer was created");
        return new ResponseEntity<>(offerDTO, HttpStatus.OK);

    }


}
