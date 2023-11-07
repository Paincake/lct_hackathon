package com.example.lct_hackathon.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="grade")
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grade_level")
    private Double gradeLevel;

    @Column(name = "grade_name")
    private String gradeName;

    @OneToMany(mappedBy = "grade")
    private List<Employee> employee;

    @ManyToMany(mappedBy = "requiredGrades")
    private List<EmployeeTask> tasks;

}
