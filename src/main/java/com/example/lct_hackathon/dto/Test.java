package com.example.lct_hackathon.dto;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("менеджер");
        System.out.println(bCryptPasswordEncoder.encode("manager"));
        System.out.println("не менеджер");
        System.out.println(bCryptPasswordEncoder.encode("agent"));
    }
    
}
