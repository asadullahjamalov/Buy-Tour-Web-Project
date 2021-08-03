package com.example.buytourwebproject.DTOs;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class OfferDTO {

    private Long id;
    private String description;
    private String travelLocations;
    private Integer price;
    private Date travelStartDate;
    private Date travelEndDate;
    private String notes;

}
