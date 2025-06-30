package com.example.studentmanagement.dto;

import com.example.studentmanagement.model.Role;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;
    
    @NotBlank(message = "role is required")
    private Role role;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "email is required")
    private String email;

    private String course;      
    private String department;  

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

     public String getName(){
        return this.name;
    }

    public String getDepartment(){
        return this.department;
    }

    public String getEmail(){
        return this.email;
    }

    public Role getRole(){
        return this.role;
    }

    public String getPassword(){
        return this.password;
    }

    public String getCourse() {
        return this.course;
    }


    // Setters
    public void setUsername(String username){
        this.username = username;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setDepartment(String department){
        this.department=department;
    }

    public void setEmail(String email){
        this.email=email;
    }
}
