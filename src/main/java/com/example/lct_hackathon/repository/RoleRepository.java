package com.example.lct_hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
