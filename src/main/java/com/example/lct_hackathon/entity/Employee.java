package com.example.lct_hackathon.entity;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "city_point_id", referencedColumnName = "id")
    private CityPoint cityPoint;

    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade grade;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "id", referencedColumnName = "employeeId")
    // private CityPoint cityPoint;
}
