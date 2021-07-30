package com.example.buytourwebproject.DTOs;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@ToString
public class RequestQueueDTO {

    private String uuid;
    private String jsonAnswers;

}
