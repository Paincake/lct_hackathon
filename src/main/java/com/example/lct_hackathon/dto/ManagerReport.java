package com.example.lct_hackathon.dto;

import com.example.lct_hackathon.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerReport {
    private Employee employee;
    private Double avgRoadTime;
    private Double avgCompletionTime;
    private Integer completedTask;
}
