package com.example.lct_hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.Employee;
import com.example.lct_hackathon.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee findById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }
}
