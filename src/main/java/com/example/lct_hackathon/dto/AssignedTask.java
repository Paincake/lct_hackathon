package com.example.lct_hackathon.dto;

import java.sql.Timestamp;
import java.util.UUID;

import lombok.Data;

@Data
public class AssignedTask {
    public void performTask(){};
    private Long empId;
    private String note;
    private String taskName;
    private Long taskId;
    private UUID taskAssignmentId;
    private String priorityName;
    private Status status;
    private Timestamp assignmentTimestamp;
    private Timestamp onWayTimestamp;
    private Timestamp startTimestamp;
    private Timestamp completionTimestamp;
    private double longitude;
    private double latitude;
}
