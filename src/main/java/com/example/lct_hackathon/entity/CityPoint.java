package com.example.lct_hackathon.entity;


import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="city_point")
@Data
public class CityPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "latitude")
    private double lat;
    
    @Column(name = "longitude")
    private double lon;

    @Column(name = "address")
    private String address;
    
    @OneToOne(mappedBy = "cityPoint")
    private Employee employee;

    @OneToOne(mappedBy = "cityPoint")
    private BusinessPoint businessPoint;

}
