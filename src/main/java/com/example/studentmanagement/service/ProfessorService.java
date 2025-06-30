package com.example.studentmanagement.service;

import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Professor;
import com.example.studentmanagement.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id " + id));
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateProfessor(Long id, Professor updatedProfessor) {
        Professor professor = getProfessorById(id);
        professor.setName(updatedProfessor.getName());
        professor.setEmail(updatedProfessor.getEmail());
        professor.setDepartment(updatedProfessor.getDepartment());
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }

    public Professor getProfessorByUsername(String username) {
        return professorRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found for username: " + username));
    }
}
