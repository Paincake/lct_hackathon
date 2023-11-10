package com.example.lct_hackathon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.repository.BusinessPointRepository;

@Service
public class BusinessPointService {
    
    @Autowired
    private BusinessPointRepository businessPointRepository;

    public List<BusinessPoint> findAll(){
        return businessPointRepository.findAll();
    }

    public String getBusinessPointAddressByLongitudeAndLatitude(Double longitude, Double latitude){
        return businessPointRepository.findAddressByLongitudeAndLatitude(longitude, latitude);
    }

    public void performDeliveryTask(double longitude, double latitude) {
        businessPointRepository.performDeliveryTask(longitude, latitude);
    }

    
    
}
