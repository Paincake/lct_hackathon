package com.example.lct_hackathon.entity;

import java.beans.JavaBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface CityPointProjection {
    String getAddress();
    double getLongitude();
    double getLatitude();
    double getDistance();
}
