package com.example.lct_hackathon.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "completed_task")
@Data
public class CompletedTask {
    @Id
    private Long id;

    @Column(name = "completion_note")
    private String completionNote;

    @Column(name = "start_timestamp")
    private Timestamp startTimestamp;

    @Column(name = "completion_timestamp")
    private Timestamp completionTimestamp;

    @Column(name = "on_way_timestamp")
    private Timestamp onWayTimestamp;

    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private EmployeeTask employeeTask;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
}
