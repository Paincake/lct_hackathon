package com.example.lct_hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.EmployeeTask;

@Repository
public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Long> {
    
}
