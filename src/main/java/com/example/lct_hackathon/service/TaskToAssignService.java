package com.example.lct_hackathon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.dto.TaskToAssign;
import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.entity.EmployeeTask;

@Service
public class TaskToAssignService {
    @Autowired
    private BusinessPointService businessPointService;

    public List<TaskToAssign> scanDatabaseTasks(){
        List<TaskToAssign> tasksToAssign = new ArrayList<>();
        List<BusinessPoint> allPoints = businessPointService.findAll();
        allPoints.forEach(p -> {
            if ((p.getApprovedRequests() > 0 && p.getDaysSinceCardRelease() > 7) || p.getDaysSinceCardRelease() > 14){
                tasksToAssign.add(new TaskToAssign(1L, 3, p.getLongitude(), p.getLatitude()));
            }
            if (p.getReleasedCards() > 0 && (double) p.getReleasedCards() / p.getApprovedRequests() < 0.5){
                tasksToAssign.add(new TaskToAssign(2L, 2, p.getLongitude(), p.getLatitude()));
            }
            if (p.isAddedYesterday() || !p.isCardsAndMaterialsDelivered()){
                tasksToAssign.add(new TaskToAssign(3L, 1, p.getLongitude(), p.getLatitude()));
            }
        });
        return tasksToAssign;
    }
}
