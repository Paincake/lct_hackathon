package com.example.lct_hackathon.dto;



public interface CompletedTaskReportProjection {
    Long getEmployeeId();
    Double getAvgRoadTime();
    Double getAvgCompletionTime();
    Integer getCompletedTasks();
}
