package com.example.lct_hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.repository.BusinessPointRepository;

@Service
public class BusinessPointService {
    
    @Autowired
    private BusinessPointRepository businessPointRepository;

    public List<BusinessPoint> getAllPoints(){
        return businessPointRepository.findAll();
    }
    
}
