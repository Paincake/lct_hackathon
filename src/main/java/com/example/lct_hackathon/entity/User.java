package com.example.lct_hackathon.entity;

import java.util.Collection;
import java.util.Set;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(this == o){
            return true;
        }
        if(o.getClass() != this.getClass()){
            return false;
        }
        User u = (User) o;
        if(
            u.id == this.id && 
            u.employeeId == this.employeeId && 
            this.username == u.username && 
            this.password == u.password && 
            u.roles.equals(this.roles)
            ){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        int constant = 10;
        int res = 1;
        res += id * constant;
        res *= employeeId + constant;
        res += username.hashCode() * constant;
        res *= password.hashCode() * constant;
        res += roles.hashCode() * constant + 10;
        return res;
    }


    
}
