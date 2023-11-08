package com.example.lct_hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.CompletedTask;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long>{
    
}
