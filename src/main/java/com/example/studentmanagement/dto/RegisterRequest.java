package com.example.studentmanagement.dto;

import com.example.studentmanagement.model.Role;

public class RegisterRequest {
    private String username;
    private String password;
    private Role role;

    // Default constructor
    public RegisterRequest() {}

    // Constructor with fields
    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
        
    }

    // Getters
    public String getUsername(){
        return this.username;
    }

    public Role getRole(){
        return this.role;
    }

    public String getPassword(){
        return this.password;
    }

    // Setters
    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void getRole(Role role){
        this.role = role;
    }
}
