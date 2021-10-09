package com.example.buytourwebproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
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
