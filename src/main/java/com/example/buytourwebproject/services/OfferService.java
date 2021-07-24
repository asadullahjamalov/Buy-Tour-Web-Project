package com.example.buytourwebproject.services;

import com.example.buytourwebproject.DTOs.OfferDTO;
import com.example.buytourwebproject.models.Offer;
import com.example.buytourwebproject.repositories.OfferRepo;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    private final OfferRepo offerRepo;

    public OfferService(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    public void createOffer(Offer offer) {
        System.out.println("***> "+offer.getId());
        System.out.println("***> "+offer);
        offerRepo.save(offer);
    }


    public OfferDTO convertModelToDTO(Offer offer) {
        OfferDTO offerDTO = OfferDTO.builder()
                .id(offer.getId())
                .description(offer.getDescription())
                .travelLocations(offer.getTravelLocations())
                .travelDate(offer.getTravelDate())
                .price(offer.getPrice())
                .notes(offer.getNotes())
                .build();
        return offerDTO;
    }
}
