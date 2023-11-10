package com.example.lct_hackathon.dto;

import java.sql.Timestamp;

import com.example.lct_hackathon.entity.Employee;

import lombok.Data;

@Data
public class AssignmentManagerInfo {
    private Employee employee;
    private String note;
    private String taskName;
    private String priorityName;
    private String status;
    private Timestamp assignmentTimestamp;
    private Timestamp onWayTimestamp;
    private Timestamp startTimestamp;
    private Timestamp completionTimestamp;
    private String address;
    
}
