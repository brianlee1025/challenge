package com.wcc.challenge.service;

import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import com.wcc.challenge.model.request.GeographicDistanceRequest;
import com.wcc.challenge.model.request.GeographicUpdateRequest;
import com.wcc.challenge.model.response.GeographicDistanceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PostCodeService {

    ResponseEntity<GeographicDistanceResponse> calculateDistance(GeographicDistanceRequest req);

    ResponseEntity<PostCodeLatLngDto> updatePostcodeCoordinates(GeographicUpdateRequest req);

}
