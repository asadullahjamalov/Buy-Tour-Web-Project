package com.example.buytourwebproject.DTOs;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class OfferDTO {

    private Long id;
    private String description;
    private String travelLocations;
    private Date travelDate;
    private Integer price;
    private String notes;

}
