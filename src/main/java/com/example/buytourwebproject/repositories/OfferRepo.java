package com.example.buytourwebproject.repositories;

import com.example.buytourwebproject.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepo extends JpaRepository<Offer, Long> {
}
