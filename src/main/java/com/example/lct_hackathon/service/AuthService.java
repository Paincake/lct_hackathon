package com.example.lct_hackathon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.repository.UserRepository;

@Service
public class AuthService {
    private static Map<UUID, User> authenticatedUsers = new HashMap<>();
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Autowired
    private UserService userService;

    public UUID login(String username, String password){
        User user = userService.findByUsername(username);
        if(encoder.matches(password, user.getPassword())){
            UUID token = UUID.randomUUID();
            authenticatedUsers.put(token, user);
            return token;
        }
        else{
            return null;
        }
    }

    public User getUser(UUID token){
        return authenticatedUsers.getOrDefault(token, null);
    }

    public User authorize(UUID token, String roleName){
        User user = getUser(token);
        if(user == null){
            return null;
        }
        boolean isAuthorized = false;
        for(Role role : user.getRoles()) {
            if(role.getName().equals(roleName)) {
                isAuthorized = true;
            }
        }
        if(isAuthorized){
            return user;
        }
        return null;
    }
}
