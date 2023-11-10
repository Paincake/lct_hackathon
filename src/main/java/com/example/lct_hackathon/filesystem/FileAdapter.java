package com.example.lct_hackathon.filesystem;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.dto.AssignedTask;
import com.example.lct_hackathon.dto.AssignedTaskFactory;
import com.example.lct_hackathon.dto.TaskToAssign;
import com.example.lct_hackathon.entity.Employee;
import com.example.lct_hackathon.service.EmployeeTaskService;
import com.example.lct_hackathon.service.TaskAssignmentService;

@Service
public class FileAdapter {

    @Value("${tasks.output.file.path}")
    private String tasksDataFilePath;

    @Value("${employee.output.file.path}")
    private String employeeDataFilePath;

    @Value("${assigned_task.output.file.path}")
    private String assignedTaskDataFilePath;

    public void writeTaskInfo(List<TaskToAssign> tasksToAssign) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(tasksDataFilePath))){
            writer.write("" + tasksToAssign.size() + "\n");
            for(int i = 0; i < tasksToAssign.size(); i++){
                TaskToAssign task = tasksToAssign.get(i);
                writer.write("" + task.getTaskId() + " " + task.getPriorityLevel() + " " + task.getLatitude() + " " + task.getLongitude());
                if(i != tasksToAssign.size() - 1){
                    writer.write("\n");
                }
            }
            writer.flush();
        }
    }

    public void writeEmployeeInfo(List<Employee> employees) throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(employeeDataFilePath))){
            writer.write("" + employees.size() + "\n");
            for(int i = 0; i < employees.size(); i++){
                Employee employee = employees.get(i);
                writer.write("" + employee.getId() + " " + employee.getGrade().getGradeLevel() + " " + employee.getLatitude() + " " + employee.getLongitude());
                if(i != employees.size() - 1){
                    writer.write("\n");
                }
            }
            writer.flush();
        }
    }

    public void readAssignedTaskInfo(AssignedTaskFactory factory, EmployeeTaskService employeeTaskService, TaskAssignmentService taskAssignmentService) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(assignedTaskDataFilePath))) {
            int n = Integer.parseInt(reader.readLine());
            for(int i = 0; i < n; i++){
                String[] idAndM = reader.readLine().split(" ");
                Long empId = Long.parseLong(idAndM[0]);
                int m = Integer.parseInt(idAndM[1]);
                for(int j = 0; j < m; j++){
                    String[] data = reader.readLine().split(" ");
                    Long taskId = Long.parseLong(data[0]);
                    double latitdue = Double.parseDouble(data[1]);
                    double longitude = Double.parseDouble(data[2]);
                    AssignedTask task = factory.createAssignedTask(empId, employeeTaskService.findById(taskId), latitdue, longitude);
                    taskAssignmentService.assignTask(empId, task);
                }
            }
        }
    }
}
