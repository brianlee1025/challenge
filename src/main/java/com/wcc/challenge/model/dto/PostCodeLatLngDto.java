package com.wcc.challenge.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostCodeLatLngDto {

    private Integer id;

    private String postcode;

    private double latitude;

    private double longitude;

}
