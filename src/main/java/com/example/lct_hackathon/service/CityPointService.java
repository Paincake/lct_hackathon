package com.example.lct_hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.CityPointProjection;
import com.example.lct_hackathon.repository.CityPointRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

@Service
public class CityPointService {
    @Autowired
    private CityPointRepository cityPointRepository;
    
    public List<CityPointProjection> getCityPointProjections(double lon, double lat){
        return cityPointRepository.findAllCityPointsOrderedByDistance(lon, lat);
    }
}
