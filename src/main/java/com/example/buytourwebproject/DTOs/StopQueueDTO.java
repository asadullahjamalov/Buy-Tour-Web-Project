package com.example.buytourwebproject.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class StopQueueDTO {
    private String uuid;
}
