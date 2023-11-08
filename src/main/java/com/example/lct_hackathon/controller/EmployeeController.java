package com.example.lct_hackathon.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.dto.Status;
import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.service.AuthService;
import com.example.lct_hackathon.service.TaskAssignmentService;

@RestController
public class EmployeeController {
    @Autowired
    private AuthService authService;

    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @GetMapping("/start_testing")
    public void test(){
        AssignedTask task = new AssignedTask();
        Timestamp timestamp = Timestamp.from(Instant.now());
        task.setAssignmentTimestamp(timestamp);
        task.setCompletionTimestamp(null);
        task.setStartTimestamp(null);
        task.setEmpId(1L);
        task.setLatitude(53.4124);
        task.setLongitude(13.12312);
        task.setNote("");
        task.setPriorityName("Высокий приоритет");
        task.setStatus(Status.ASSIGNED);
        task.setTaskId(1L);
        task.setTaskAssignmentId(UUID.randomUUID());
        taskAssignmentService.assignTask(1L, task);
    }

    @GetMapping("/tasks")
    public ResponseEntity<Collection<AssignedTask>> getTasks(@RequestParam UUID token){
        User user = authService.getUser(token);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        boolean isAuthorized = false;

        for(Role role : user.getRoles()) {
            if(role.getName() == "AGENT") {
                isAuthorized = true;
            }
        }

        if(!isAuthorized){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Long empId = user.getEmployeeId();
        return new ResponseEntity<>(taskAssignmentService.getEmployeeTasks(empId).values(), HttpStatus.OK);
    }

    @PostMapping("/task/{taskAssignmentId}")
    public ResponseEntity<String> changeTaskStatus(@RequestParam UUID token, @RequestParam String status, @RequestParam String note, @PathVariable Long taskAssignmentId){
        User user = authService.getUser(token);
        if(user == null){
            return new ResponseEntity<>("Authorization falied", HttpStatus.UNAUTHORIZED);
        }
         boolean isAuthorized = false;

        for(Role role : user.getRoles()) {
            if(role.getName() == "AGENT") {
                isAuthorized = true;
            }
        }

        if(!isAuthorized){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long empId = user.getEmployeeId();
        taskAssignmentService.changeTaskStatus(Status.valueOf(status), note, taskAssignmentId, empId);
        return new ResponseEntity<>(HttpStatus.OK); 
    }
    
}
