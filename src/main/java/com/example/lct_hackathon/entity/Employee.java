package com.example.lct_hackathon.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "city_point_id", referencedColumnName = "id")
    private CityPoint cityPoint;

    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade grade;

    @OneToMany(mappedBy = "employee")
    private List<CompletedTask> completedTasks;
    
}
