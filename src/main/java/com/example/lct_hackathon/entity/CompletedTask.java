package com.example.lct_hackathon.entity;

import java.sql.Timestamp;

import com.example.lct_hackathon.dto.CompletedTaskReportProjection;

import jakarta.persistence.CascadeType;
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "task_id")
    private EmployeeTask employeeTask;

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    @JoinColumn(name="employee_id")
    private Employee employee;
}
