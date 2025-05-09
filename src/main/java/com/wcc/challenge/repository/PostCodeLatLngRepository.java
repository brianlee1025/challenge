package com.wcc.challenge.repository;

import com.wcc.challenge.model.entity.PostCodeLatLng;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostCodeLatLngRepository extends JpaRepository<PostCodeLatLng ,Integer> {

    Optional<PostCodeLatLng> findByPostcode(String postcode);

}
