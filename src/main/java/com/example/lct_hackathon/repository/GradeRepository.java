package com.example.lct_hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByGradeName(String gradeName);
}
