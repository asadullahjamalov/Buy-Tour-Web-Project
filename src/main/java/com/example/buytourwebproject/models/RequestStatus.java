package com.example.buytourwebproject.models;

import com.example.buytourwebproject.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agent_requests")
public class RequestStatus {

    @Id
    private Long id;


    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
    private RequestType requestType;

    @OneToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    Agent agent;

}
