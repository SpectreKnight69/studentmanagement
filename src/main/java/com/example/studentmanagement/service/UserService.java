package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.RegisterRequest;
import com.example.studentmanagement.model.Professor;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.model.User;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.model.Role;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User register(RegisterRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

         if (request.getRole() == Role.ROLE_STUDENT) {
        Student student = new Student();
        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCourse(request.getCourse());
        student.setUser(user);
        user.setStudent(student);
        }
         else if (request.getRole() == Role.ROLE_PROFESSOR) {
        Professor professor = new Professor();
        professor.setName(request.getName());
        professor.setEmail(request.getEmail());
        professor.setDepartment(request.getDepartment());
        professor.setUser(user);
        user.setProfessor(professor);
    }
        return userRepository.save(user);
    }

    public String getCurrentUserProfile() {
        return "User Profile Data - Authentication Working!";
    }
}
