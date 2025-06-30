package com.example.studentmanagement.model;

import jakarta.persistence.*;
import lombok.*;
import com.example.studentmanagement.model.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Professor professor;

    // Default constructor
    public User() {}

    // Constructor with fields
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        // this.student = student;
        // this.professor = professor;
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole(){
        return this.role;
    }

    public Student getStudent() {
        return this.student;
    }

    public Professor getProfessor() {
        return this.professor;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role){
        this.role=role;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
