package com.example.buytourwebproject.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name = "agents")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String agencyName;
    String TIN;
    String agentName;
    String agentSurname;
    String email;
    String password;
    LocalDate createdDate;


}
