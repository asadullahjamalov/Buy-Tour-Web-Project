package com.example.buytourwebproject.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agent_confirm_tokens")
public class AgentConfirmToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String confirmationToken;

    private LocalDateTime createdDate ;

    @OneToOne(targetEntity = Agent.class)
    @JoinColumn(nullable = false, name = "agent_id")
    private Agent agent;
}
