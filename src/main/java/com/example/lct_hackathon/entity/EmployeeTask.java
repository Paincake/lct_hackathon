package com.example.lct_hackathon.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="employee_task")
@Data
public class EmployeeTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id")
    private TaskPriority taskPriority;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "completion_time")
    private double completionTime;

    @Column(name = "condition_one")
    private String conditionOne;

    @Column(name = "condition_two")
    private String conditionTwo;

    @ManyToMany(cascade={CascadeType.PERSIST})
    private List<Grade> requiredGrades;

}
