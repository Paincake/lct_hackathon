package com.example.lct_hackathon.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.dto.Status;

@Service
public class TaskAssignmentService {
    
    @Autowired
    private CompletedTaskService completedTaskService;

    private static Map<Long, Map<UUID, AssignedTask>> assignedTaskMap = new HashMap<>();
    
    public void assignTask(Long empId, AssignedTask assignedTask){
        if(!assignedTaskMap.containsKey(empId)){
            assignedTaskMap.put(empId, new HashMap<>());
        }
        assignedTaskMap.get(empId).put(assignedTask.getTaskAssignmentId(), assignedTask);
        
    }
    
    public Map<UUID, AssignedTask> getEmployeeTasks(Long empId) {
        return assignedTaskMap.get(empId);
    }

    public Collection<Map<UUID, AssignedTask>> getTasks() {
        return assignedTaskMap.values();
    }

    public void changeTaskStatus(Status status, String note, Long taskAssignmentId, Long empId){
        AssignedTask task = assignedTaskMap.get(empId).get(taskAssignmentId);
        Timestamp timestamp = Timestamp.from(Instant.now());
        task.setStatus(status);
        switch(status){
            case DONE -> {
                task.setCompletionTimestamp(timestamp);
                task.setNote(note);
                task.performTask();
                completedTaskService.save(task);
            }
            case ON_WAY -> {
                task.setOnWayTimestamp(timestamp);
            }
            case IN_PROGRESS -> {
                task.setStartTimestamp(timestamp);
                if(task.getOnWayTimestamp() == null){
                    task.setOnWayTimestamp(timestamp);
                }
            }
            default -> {}
        }
        
    }
}

