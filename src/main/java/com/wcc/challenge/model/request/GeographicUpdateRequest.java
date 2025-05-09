package com.wcc.challenge.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GeographicUpdateRequest {

    @NotNull(message = "postcodeData is mandatory")
    @JsonProperty(value = "postcodeData")
    private PostCodeLatLngDto postCodeLatLngDto;
}
