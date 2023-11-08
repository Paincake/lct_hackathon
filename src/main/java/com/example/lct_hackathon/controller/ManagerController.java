package com.example.lct_hackathon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.lct_hackathon.service.AuthService;

@RestController
public class ManagerController {
    @Autowired
    private AuthService authService;
    
}
