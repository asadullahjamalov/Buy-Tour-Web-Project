package com.example.buytourwebproject.services.interfaces;


import com.example.buytourwebproject.DTOs.OfferQueueDTO;

public interface RabbitMQService {

    void send(OfferQueueDTO offerQueueDTO);

}
