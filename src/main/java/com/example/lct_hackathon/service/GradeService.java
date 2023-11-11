package com.example.lct_hackathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.Grade;
import com.example.lct_hackathon.repository.GradeRepository;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public Grade findByGradeName(String gradeName){
        return gradeRepository.findByGradeName(gradeName);
    }
}
