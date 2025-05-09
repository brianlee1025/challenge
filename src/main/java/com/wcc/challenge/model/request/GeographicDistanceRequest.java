package com.wcc.challenge.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeographicDistanceRequest {

    @NotBlank(message = "from is mandatory")
    @JsonProperty(value = "from")
    private String postCodeFrom;

    @NotBlank(message = "to is mandatory")
    @JsonProperty(value = "to")
    private String postCodeTo;
}
