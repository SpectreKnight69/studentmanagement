package com.example.studentmanagement.controller;

import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Professor;
import com.example.studentmanagement.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.studentmanagement.repository.UserRepository;
import com.example.studentmanagement.model.User;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Professor> getAllProfessors() {
        return professorService.getAllProfessors();
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('PROFESSOR')")
    public Professor getOwnProfessorInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        
        if (user.getProfessor() == null) {
            throw new ResourceNotFoundException("No professor data linked with this user.");
        }

        return user.getProfessor();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PROFESSOR')")
    public Professor getProfessorById(@PathVariable Long id) {
        return professorService.getProfessorById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Professor createProfessor(@Valid @RequestBody Professor professor) {
        return professorService.createProfessor(professor);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Professor updateProfessor(@PathVariable Long id, @Valid @RequestBody Professor professor) {
        return professorService.updateProfessor(id, professor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
    }
}
