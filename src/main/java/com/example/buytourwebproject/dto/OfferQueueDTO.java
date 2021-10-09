package com.example.buytourwebproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class OfferQueueDTO  {
    private Long agentId;
    private String uuid;
    private byte[] image;
}
