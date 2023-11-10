package com.example.lct_hackathon.dto;

import com.example.lct_hackathon.service.BusinessPointService;

public class DeliveryAssignedTask extends AssignedTask {
    public void performTask(BusinessPointService businessPointService) {
        businessPointService.performDeliveryTask(longitude, latitude);
    }
    
}
