package com.example.buytourwebproject.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class StopQueueDTO {
    private String uuid;
}
