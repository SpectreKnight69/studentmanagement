package com.example.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Course cannot be empty")
    private String course;

    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    // Default constructor
    public Student() {}

    // Constructor with fields
    public Student(Long id, String name, String course, String email, User user) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.email = email;
        this.user = user;
    }

    // Getters
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCourse() {
        return this.course;
    }

    public String getEmail() {
        return this.email;
    }

    public User getUser() {
        return this.user;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
