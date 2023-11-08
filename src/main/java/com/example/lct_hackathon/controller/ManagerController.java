package com.example.lct_hackathon.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.entity.Employee;
import com.example.lct_hackathon.entity.EmployeeTask;
import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.service.AuthService;
import com.example.lct_hackathon.service.BusinessPointService;
import com.example.lct_hackathon.service.EmployeeService;
import com.example.lct_hackathon.service.EmployeeTaskService;
import com.example.lct_hackathon.service.TaskAssignmentService;

@RestController
public class ManagerController {
    @Autowired
    private AuthService authService;

    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @Autowired
    private BusinessPointService businessPointService;

    @Autowired
    private EmployeeTaskService employeeTaskService;

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/tasks/status")
    public ResponseEntity<Collection<Map<UUID, AssignedTask>>> getAssignedTasks(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<Collection<Map<UUID, AssignedTask>>>(taskAssignmentService.getTasks(), HttpStatus.OK);

    }

    @GetMapping("/business_points")
    public ResponseEntity<List<BusinessPoint>> getBusinessPoints(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(businessPointService.getAllPoints(), HttpStatus.OK);
    }

    @GetMapping("/tasks/data")
    public ResponseEntity<List<EmployeeTask>> getEmployeeTasks(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(employeeTaskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    // @PutMapping("/employee/{empId}")
    // public ResponseEntity<Employee> changeEmployee(@RequestParam UUID token, @PathVariable("empId") Long empId, @RequestBody Employee employee){
    //     User user = authService.authorize(token, "MANAGER");
    //     if(user == null){
    //         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    //     }
    //     employee.setId(empId);
    //     Employee emp = employeeService.save(employee);
    //     return new ResponseEntity<>(emp, HttpStatus.ACCEPTED);
    // }

}
