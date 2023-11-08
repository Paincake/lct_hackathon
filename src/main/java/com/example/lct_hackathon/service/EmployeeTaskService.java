package com.example.lct_hackathon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.EmployeeTask;
import com.example.lct_hackathon.repository.EmployeeTaskRepository;

@Service
public class EmployeeTaskService {
    @Autowired
    private EmployeeTaskRepository employeeTaskRepository;

    public EmployeeTask findById(Long id){
        return employeeTaskRepository.findById(id).orElse(null);
    }
    public List<EmployeeTask> findAll(){
        return employeeTaskRepository.findAll();
    }
}
