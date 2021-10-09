package com.example.buytourwebproject.service.interfaces;


import com.example.buytourwebproject.dto.OfferQueueDTO;

public interface RabbitMQService {

    void send(OfferQueueDTO offerQueueDTO);

}
