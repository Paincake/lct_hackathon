package com.example.lct_hackathon.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.entity.EmployeeTask;
import com.example.lct_hackathon.repository.EmployeeTaskRepository;

@Service
public class EmployeeTaskService {
    
    private static final Long FIRST_TASK_ID = 1L;
     
    private static final Long SECOND_TASK_ID = 2L;
     
    private static final Long THIRD_TASK_ID = 3L;

    @Autowired
    private EmployeeTaskRepository employeeTaskRepository;

    @Autowired 
    private BusinessPointService businessPointService;

    public EmployeeTask findById(Long id){
        return employeeTaskRepository.findById(id).orElse(null);
    }
    public List<EmployeeTask> findAll(){
        return employeeTaskRepository.findAll();
    }

    
}
