package com.example.lct_hackathon.dto;

import java.sql.Timestamp;
import java.util.UUID;

import com.example.lct_hackathon.service.BusinessPointService;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class AssignedTask {
    public abstract void performTask(BusinessPointService businessPointService);
    protected Long empId;
    protected String note;
    protected String taskName;
    protected Long taskId;
    protected UUID taskAssignmentId;
    protected String priorityName;
    protected Status status;
    protected Timestamp assignmentTimestamp;
    protected Timestamp onWayTimestamp;
    protected Timestamp startTimestamp;
    protected Timestamp completionTimestamp;
    protected double longitude;
    protected double latitude;
}
