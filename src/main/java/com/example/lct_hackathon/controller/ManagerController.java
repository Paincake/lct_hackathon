package com.example.lct_hackathon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.dto.AssignedTaskFactory;
import com.example.lct_hackathon.dto.AssignmentManagerInfo;
import com.example.lct_hackathon.dto.CompletedTaskReportProjection;
import com.example.lct_hackathon.dto.ManagerReport;
import com.example.lct_hackathon.dto.TaskToAssign;
import com.example.lct_hackathon.entity.BusinessPoint;
import com.example.lct_hackathon.entity.CompletedTask;
import com.example.lct_hackathon.entity.Employee;
import com.example.lct_hackathon.entity.EmployeeTask;
import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.filesystem.FileAdapter;
import com.example.lct_hackathon.service.AuthService;
import com.example.lct_hackathon.service.BusinessPointService;
import com.example.lct_hackathon.service.CompletedTaskService;
import com.example.lct_hackathon.service.EmployeeService;
import com.example.lct_hackathon.service.EmployeeTaskService;
import com.example.lct_hackathon.service.TaskAssignmentService;
import com.example.lct_hackathon.service.TaskToAssignService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@RestController
@RequestMapping(produces="application/json")
@CrossOrigin(origins={"http://localhost:3000", "http://localhost:8080", "http://localhost:8081", "http://94.139.254.37:8081", "http://94.139.254.37:8080"})
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

    @Autowired
    private CompletedTaskService completedTaskService;

    @Autowired
    private FileAdapter fileAdapter;

    @Autowired
    private TaskToAssignService taskToAssignService;

    @Value("${flask.app.address}")
    private String flaskAppAddress;

    @GetMapping("/tasks/assign")
    public ResponseEntity<String> assignTasks(@RequestParam UUID token){

        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        RestTemplate restTemplate = new RestTemplate();
        try{
            fileAdapter.writeEmployeeInfo(employeeService.findAllEmployees());
            fileAdapter.writeTaskInfo(taskToAssignService.scanDatabaseTasks());
        }
        catch (IOException exc) {
            return new ResponseEntity<>("Ошибка записи информации", HttpStatusCode.valueOf(405));
        }
        ResponseEntity<String> response = restTemplate.getForEntity(flaskAppAddress + "/algorithm", String.class);
        if(response.getStatusCode().equals(HttpStatusCode.valueOf(200))){
            try{
                fileAdapter.readAssignedTaskInfo(new AssignedTaskFactory(), employeeTaskService, taskAssignmentService);
            }
            catch (IOException exc) {
                return new ResponseEntity<>("Ошибка чтения информации", HttpStatusCode.valueOf(405));
            }
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/tasks/status")
    public ResponseEntity<List<AssignmentManagerInfo>> getAssignedTasks(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Map<UUID, AssignedTask> map = taskAssignmentService.getTasks().get(0);
        List<AssignmentManagerInfo> assignmentManagerInfoList = new ArrayList<>();

        for(Map.Entry<UUID, AssignedTask> entry : map.entrySet()){
            AssignedTask task = entry.getValue();
            Employee employee = employeeService.findById(task.getEmpId());
            AssignmentManagerInfo managerInfo = new AssignmentManagerInfo();
            managerInfo.setEmployee(employee);
            managerInfo.setAssignmentTimestamp(task.getAssignmentTimestamp());
            managerInfo.setCompletionTimestamp(task.getCompletionTimestamp());
            managerInfo.setOnWayTimestamp(task.getOnWayTimestamp());
            managerInfo.setStartTimestamp(task.getStartTimestamp());
            managerInfo.setNote(task.getNote());
            managerInfo.setStatus(task.getStatus().name());
            managerInfo.setTaskName(task.getTaskName());
            managerInfo.setPriorityName(task.getPriorityName());
            managerInfo.setAddress(businessPointService.getBusinessPointAddressByLongitudeAndLatitude(task.getLongitude(), task.getLatitude()));
            assignmentManagerInfoList.add(managerInfo);
        }
        return new ResponseEntity<List<AssignmentManagerInfo>>(assignmentManagerInfoList, HttpStatus.OK);

    }

    @GetMapping("/business_points")
    public ResponseEntity<List<BusinessPoint>> getBusinessPoints(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(businessPointService.findAll(), HttpStatus.OK);
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

    @GetMapping("/report")
    public ResponseEntity<List<ManagerReport>> getReport(@RequestParam UUID token){
        User user = authService.authorize(token, "MANAGER");
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<CompletedTaskReportProjection> tasks = completedTaskService.findCompletedTaskReportProjections();
        List<ManagerReport> reportList = new ArrayList<>();
        for(CompletedTaskReportProjection task : tasks){
            reportList.add(new ManagerReport(employeeService.findById(task.getEmployeeId()), task.getAvgRoadTime(), task.getAvgCompletionTime(), task.getCompletedTasks()));
        }
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    
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
