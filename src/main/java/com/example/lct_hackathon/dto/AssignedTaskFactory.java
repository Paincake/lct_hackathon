package com.example.lct_hackathon.dto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import com.example.lct_hackathon.entity.EmployeeTask;
import com.example.lct_hackathon.service.EmployeeTaskService;

import lombok.Data;

@Data
public class AssignedTaskFactory {
    public AssignedTask createAssignedTask(Long empId, EmployeeTask employeeTask, double latitude, double longitude) {
        AssignedTask task = null;
        int taskId = (int) employeeTask.getId().longValue();
        switch (taskId) {
            case 1 -> {task = new StimulationAssignedTask();}
            case 2 -> {task = new AgentTrainAssignedTask();}
            case 3 -> {task = new DeliveryAssignedTask();}
        }

        task.setEmpId(empId);
        task.setTaskId(employeeTask.getId());
        task.setNote("");
        task.setTaskName(employeeTask.getTaskName());
        task.setTaskAssignmentId(UUID.randomUUID());
        task.setPriorityName(employeeTask.getTaskPriority().getPriorityName());
        task.setStatus(Status.ASSIGNED);
        task.setAssignmentTimestamp(Timestamp.from(Instant.now()));
        task.setOnWayTimestamp(null);
        task.setStartTimestamp(null);
        task.setCompletionTimestamp(null);
        task.setLongitude(longitude);
        task.setLatitude(latitude);

        return task;

    }
}
