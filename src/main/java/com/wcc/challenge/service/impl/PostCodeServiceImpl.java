package com.wcc.challenge.service.impl;

import com.wcc.challenge.mapper.PostCodeLatLngMapper;
import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import com.wcc.challenge.model.entity.PostCodeLatLng;
import com.wcc.challenge.model.request.GeographicDistanceRequest;
import com.wcc.challenge.model.request.GeographicUpdateRequest;
import com.wcc.challenge.model.response.GeographicDistanceResponse;
import com.wcc.challenge.repository.PostCodeLatLngRepository;
import com.wcc.challenge.service.PostCodeService;
import com.wcc.challenge.utils.GeographicUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PostCodeServiceImpl implements PostCodeService {

    private final PostCodeLatLngRepository postCodeLatLngRepository;
    private static final String UNIT = "km";

    @Override
    public ResponseEntity<GeographicDistanceResponse> calculateDistance(GeographicDistanceRequest req) {
        GeographicDistanceResponse res = GeographicDistanceResponse.builder()
                .unit(UNIT)
                .build();

        PostCodeLatLng postCodeLatLngFrom = postCodeLatLngRepository.findByPostcode(req.getPostCodeFrom())
                .orElseThrow(() -> new RuntimeException("Postcode - from does not exist!"));
        PostCodeLatLng postCodeLatLngTo = postCodeLatLngRepository.findByPostcode(req.getPostCodeTo())
                .orElseThrow(() -> new RuntimeException("Postcode - to does not exist!"));

        res.setPostCodeFromLatLngDto(
                PostCodeLatLngMapper.INSTANCE.postCodeLatLngToPostCodeLatLngDto(
                        postCodeLatLngFrom));

        res.setPostCodeToLatLngDto(
                PostCodeLatLngMapper.INSTANCE.postCodeLatLngToPostCodeLatLngDto(
                        postCodeLatLngTo));

        res.setDistanceInBetween(
                GeographicUtil.calculateDistance(
                        postCodeLatLngFrom.getLatitude(), postCodeLatLngFrom.getLongitude(),
                        postCodeLatLngTo.getLatitude(), postCodeLatLngTo.getLongitude()
                        )
        );

        return ResponseEntity.ok(res);
    }

    @Override
    @Transactional
    public ResponseEntity<PostCodeLatLngDto> updatePostcodeCoordinates(GeographicUpdateRequest req) {
        PostCodeLatLng postCodeLatLng = postCodeLatLngRepository.findByPostcode(
                req.getPostCodeLatLngDto().getPostcode()).orElseThrow(() -> new RuntimeException("Postcode - from does not exist!"));

        postCodeLatLng.setLatitude(req.getPostCodeLatLngDto().getLatitude());
        postCodeLatLng.setLongitude(req.getPostCodeLatLngDto().getLongitude());

        return ResponseEntity.ok(
                PostCodeLatLngMapper.INSTANCE.postCodeLatLngToPostCodeLatLngDto(
                        postCodeLatLngRepository.save(postCodeLatLng)));
    }
}
