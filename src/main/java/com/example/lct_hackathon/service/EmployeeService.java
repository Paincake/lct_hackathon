package com.example.lct_hackathon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.Employee;
import com.example.lct_hackathon.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserService userService;
    
    public Employee findById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    
    public List<Employee> findAllEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> agents = new ArrayList<>();
        for(Employee employee : employees){
            if(employee.getId() != 100){
                agents.add(employee);
            }
        }
        return agents;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
}
