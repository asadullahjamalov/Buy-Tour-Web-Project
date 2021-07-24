package com.example.buytourwebproject.DTOs;

import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class AgentDTO {

    private long id;
    private String agencyName;
    private String TIN;
    private String agentName;
    private String agentSurname;
    private String email;
    private LocalDate createdDate;
}

