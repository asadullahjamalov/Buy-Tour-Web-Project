package com.example.buytourwebproject.models;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "offers")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private String travelLocations;
    private Date travelDate;
    private Integer price;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;





}
