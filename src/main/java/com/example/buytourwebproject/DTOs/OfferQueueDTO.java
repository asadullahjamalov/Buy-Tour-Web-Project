package com.example.buytourwebproject.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ToString
public class OfferQueueDTO  {
    private Long agentId;
    private String uuid;
    private byte[] image;
}
