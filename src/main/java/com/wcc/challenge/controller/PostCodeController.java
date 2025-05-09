package com.wcc.challenge.controller;

import com.wcc.challenge.logging.AuditLogging;
import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import com.wcc.challenge.model.request.GeographicDistanceRequest;
import com.wcc.challenge.model.request.GeographicUpdateRequest;
import com.wcc.challenge.model.response.GeographicDistanceResponse;
import com.wcc.challenge.service.PostCodeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/postcode")
@AllArgsConstructor
public class PostCodeController {

    private final PostCodeService postCodeService;

    @RequestMapping(method = RequestMethod.PUT)
    @AuditLogging(action = "Update Postcode")
    public ResponseEntity<PostCodeLatLngDto> updatePostcode(
            @Valid @RequestBody GeographicUpdateRequest request) {
        return postCodeService.updatePostcodeCoordinates(request);
    }

    @RequestMapping(value = "/distance", method = RequestMethod.POST)
    @AuditLogging(action = "Find Distance")
    public ResponseEntity<GeographicDistanceResponse> findDistance(
            @Valid @RequestBody GeographicDistanceRequest request) {
        return postCodeService.calculateDistance(request);
    }
}
