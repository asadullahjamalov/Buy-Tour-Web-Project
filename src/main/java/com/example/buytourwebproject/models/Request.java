package com.example.buytourwebproject.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request {
    @Id
    private Long id;

    private String language;
    private String travelType;
    private String addressTo;
    private String addressFrom;
    private LocalDate localDate;
    private String numberOfPeople;
    private Integer budget;
    private LocalDate createdDate;
    private LocalDate expireDate;
    private Boolean isArchived;

}
