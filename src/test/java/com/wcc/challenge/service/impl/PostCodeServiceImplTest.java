package com.wcc.challenge.service.impl;

import com.wcc.challenge.model.dto.PostCodeLatLngDto;
import com.wcc.challenge.model.entity.PostCodeLatLng;
import com.wcc.challenge.model.request.GeographicDistanceRequest;
import com.wcc.challenge.model.request.GeographicUpdateRequest;
import com.wcc.challenge.model.response.GeographicDistanceResponse;
import com.wcc.challenge.repository.PostCodeLatLngRepository;
import com.wcc.challenge.service.PostCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostCodeServiceImplTest {

    @Mock
    private PostCodeLatLngRepository postCodeLatLngRepository;

    @InjectMocks
    private PostCodeServiceImpl postCodeService;

    @BeforeEach
    public void setUp() {
        postCodeLatLngRepository = mock(PostCodeLatLngRepository.class);
        postCodeService = new PostCodeServiceImpl(postCodeLatLngRepository);
        lenient().when(postCodeLatLngRepository.findByPostcode(null)).thenReturn(null);
        lenient().when(postCodeLatLngRepository.findByPostcode("SW1A 2AA")).thenReturn(Optional.of(mockPostCodeLatLngAA()));
        lenient().when(postCodeLatLngRepository.findByPostcode("SW1A 2BB")).thenReturn(Optional.of(mockPostCodeLatLngBB()));
        lenient().when(postCodeLatLngRepository.save(any())).thenReturn(mockPostCodeLatLngAA());
    }

    @Test
    public void givenInvalidPostcode_whenCalculateDistance_thenThrowException() {
        GeographicDistanceRequest req = new GeographicDistanceRequest();

        assertThrows(RuntimeException.class, () -> postCodeService.calculateDistance(req));
    }

    @Test
    public void givenValidPostcode_whenCalculateDistance_thenReturnValidResponse() {
        GeographicDistanceRequest req = new GeographicDistanceRequest();
        req.setPostCodeFrom("SW1A 2AA");
        req.setPostCodeTo("SW1A 2BB");

        ResponseEntity<GeographicDistanceResponse> res = postCodeService.calculateDistance(req);

        assertNotNull(res);
        assertNotNull(res.getBody());
        assertTrue(res.getBody().getDistanceInBetween() > 0.0);
        assertEquals(HttpStatus.OK, res.getStatusCode());
    }

    @Test
    public void givenInvalidPostcode_whenUpdatePostcode_thenThrowException() {
        GeographicUpdateRequest req = new GeographicUpdateRequest();

        assertThrows(RuntimeException.class, () -> postCodeService.updatePostcodeCoordinates(req));
    }

    @Test
    public void givenValidPostcode_whenUpdatePostcode_thenReturnValidResponse() {
        GeographicUpdateRequest req = new GeographicUpdateRequest();
        req.setPostCodeLatLngDto(mockPostCodeLatLngBBDto());

        ResponseEntity<PostCodeLatLngDto> res = postCodeService.updatePostcodeCoordinates(req);

        assertNotNull(res);
        assertEquals(HttpStatus.OK, res.getStatusCode());
        assertEquals(mockPostCodeLatLngAADto(), res.getBody());
    }

    private GeographicDistanceResponse  mockGeographicDistanceResponse() {
        return GeographicDistanceResponse.builder()
                .unit("km")
                .distanceInBetween(0.0)
                .postCodeFromLatLngDto(mockPostCodeLatLngAADto())
                .postCodeToLatLngDto(mockPostCodeLatLngBBDto())
                .build();
    }

    private PostCodeLatLngDto mockPostCodeLatLngAADto() {
        PostCodeLatLngDto postCodeLatLngDto = new PostCodeLatLngDto();
        postCodeLatLngDto.setPostcode("SW1A 2AA");
        postCodeLatLngDto.setLatitude(51.5074);
        postCodeLatLngDto.setLongitude(-0.1278);
        return postCodeLatLngDto;
    }

    private PostCodeLatLngDto mockPostCodeLatLngBBDto() {
        PostCodeLatLngDto postCodeLatLngDto = new PostCodeLatLngDto();
        postCodeLatLngDto.setPostcode("SW1A 2BB");
        postCodeLatLngDto.setLatitude(59.5074);
        postCodeLatLngDto.setLongitude(-1.1278);
        return postCodeLatLngDto;
    }

    private PostCodeLatLng mockPostCodeLatLngAA() {
        PostCodeLatLng postCodeLatLng = new PostCodeLatLng();
        postCodeLatLng.setPostcode("SW1A 2AA");
        postCodeLatLng.setLatitude(51.5074);
        postCodeLatLng.setLongitude(-0.1278);
        return postCodeLatLng;
    }

    private PostCodeLatLng mockPostCodeLatLngBB() {
        PostCodeLatLng postCodeLatLng = new PostCodeLatLng();
        postCodeLatLng.setPostcode("SW1A 2BB");
        postCodeLatLng.setLatitude(59.5074);
        postCodeLatLng.setLongitude(-1.1278);
        return postCodeLatLng;
    }
}
