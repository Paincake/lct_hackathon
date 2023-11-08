package com.example.lct_hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.entity.CompletedTask;
import com.example.lct_hackathon.repository.CompletedTaskRepository;


@Service
public class CompletedTaskService {
    @Autowired
    private CompletedTaskRepository completedTaskRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    public void save(AssignedTask assignedTask){
        CompletedTask completedTask = new CompletedTask();
        completedTask.setCompletionTimestamp(assignedTask.getCompletionTimestamp());
        completedTask.setEmployee(employeeService.findById(assignedTask.getEmpId()));
        completedTask.setOnWayTimestamp(assignedTask.getOnWayTimestamp());
        completedTask.setStartTimestamp(assignedTask.getStartTimestamp());
        completedTask.setEmployeeTask(employeeTaskService.findById(assignedTask.getTaskId()));
        completedTask.setCompletionNote(assignedTask.getNote());
        completedTaskRepository.save(completedTask);
    }
}
