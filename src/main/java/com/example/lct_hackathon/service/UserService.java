package com.example.lct_hackathon.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lct_hackathon.entity.Role;
import com.example.lct_hackathon.entity.User;
import com.example.lct_hackathon.repository.RoleRepository;
import com.example.lct_hackathon.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void save(User user){
        userRepository.save(user);
    }
}
