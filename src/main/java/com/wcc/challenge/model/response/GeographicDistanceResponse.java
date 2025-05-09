package com.wcc.challenge.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeographicDistanceResponse {

    @JsonProperty(value = "postCodeFrom")
    private PostCodeLatLngDto postCodeFromLatLngDto;

    @JsonProperty(value = "postCodeTo")
    private PostCodeLatLngDto postCodeToLatLngDto;

    @JsonProperty(value = "distanceInBetween")
    private double distanceInBetween;

    @JsonProperty(value = "unit")
    private String unit;

}
