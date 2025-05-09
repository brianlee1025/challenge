package com.wcc.challenge.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "postcodelatlng")
@Data
public class PostCodeLatLng {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

}
