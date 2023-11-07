package com.example.lct_hackathon.controller;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.repository.RoleRepository;
import com.example.lct_hackathon.service.AuthService;
import com.example.lct_hackathon.service.UserService;

@RestController
public class LoginController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<UUID> authenticate(@RequestParam String username, @RequestParam String password){
        UUID token = authService.authenticate(username, password);
        if(token == null){
            return new ResponseEntity<UUID>(token, HttpStatus.FORBIDDEN);
        }
        else{
            return new ResponseEntity<UUID>(token, HttpStatus.OK);
        }
    }
    @PostMapping("/register")
    public void register(@RequestParam String username, @RequestParam String password, @RequestParam Long empId){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(password);
        User user = new User();
        user.setEmployeeId(empId);
        Role agentRole = roleRepository.findById(1L).get();
        user.setRoles(Set.of(agentRole));
        user.setPassword(encoded);
        user.setUsername(username);
        userService.save(user);
    }
}
