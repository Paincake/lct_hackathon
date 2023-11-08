package com.example.lct_hackathon.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.lct_hackathon.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query(nativeQuery = true, value = """
        SELECT u.employee_id, u.id, u.password, u.username 
        FROM t_user u 
        JOIN t_user_roles tur ON u.id = tur.user_id 
        WHERE tur.roles_id != 2;
            """)
    List<User> findAll();
}
