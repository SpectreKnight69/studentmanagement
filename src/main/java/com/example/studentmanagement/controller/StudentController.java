package com.example.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.mapper.StudentMapper;

import jakarta.validation.Valid;

import com.example.studentmanagement.model.Student;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR', 'STUDENT')")
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentDTO getOwnStudentInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return StudentMapper.toDTO(user.getStudent()); 
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public StudentDTO createStudent(@Valid @RequestBody StudentDTO student){
        try {
            return studentService.createStudent(student).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create student", e);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public StudentDTO updateStudent(@PathVariable Long id,@Valid @RequestBody StudentDTO student){
        return studentService.updateStudent(id,student);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteStudent(@PathVariable Long id){
         studentService.deleteStudent(id);
    }

    @DeleteMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllStudents(){
        studentService.deleteAllStudents();
    }

}
