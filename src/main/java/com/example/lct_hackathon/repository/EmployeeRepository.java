package com.example.lct_hackathon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
}
