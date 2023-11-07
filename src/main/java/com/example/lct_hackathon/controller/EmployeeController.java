package com.example.lct_hackathon.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.entity.CityPointProjection;
import com.example.lct_hackathon.service.BusinessPointService;
import com.example.lct_hackathon.service.CityPointService;

@RestController
public class EmployeeController {
    @Autowired
    private BusinessPointService businessPointService;

    @Autowired 
    private CityPointService cityPointService;

    @GetMapping("/business_points")
    public ResponseEntity<List<BusinessPoint>> getBusinessPoints(){
        return new ResponseEntity<>(businessPointService.getAllPoints(), HttpStatus.OK);
    }

    @GetMapping("/city_points")
    public ResponseEntity<List<CityPointProjection>> getCityPoints(){
        return new ResponseEntity<>(cityPointService.getCityPointProjections(30.315212, 59.938879), HttpStatus.OK);
    }
    
}
