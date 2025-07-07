package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ProfessorDTO;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.model.Professor;
import com.example.studentmanagement.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentmanagement.mapper.ProfessorMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProfessorDTO> getAllProfessors() {
        return professorRepository.findAll()
            .stream()
            .map(ProfessorMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Cacheable(value = "professor", key = "#id")
    public ProfessorDTO getProfessorById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found with id " + id));
                return ProfessorMapper.toDTO(professor);
            }

        public Professor getProfessorEntityById(Long id){
        Optional<Professor> professor =  professorRepository.findById(id);
        return professor.get();
    }

    public ProfessorDTO createProfessor(ProfessorDTO dto) {
        Professor professor = ProfessorMapper.toEntity(dto);
        return ProfessorMapper.toDTO(professorRepository.save(professor));
    }

    @CacheEvict(value = "professor",key = "#id")
    public ProfessorDTO updateProfessor(Long id, ProfessorDTO dto) {
       Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found"));
        professor.setName(dto.getName());
        professor.setEmail(dto.getEmail());
        professor.setDepartment(dto.getDepartment());
        return ProfessorMapper.toDTO(professorRepository.save(professor));
    }
    @CacheEvict(value = "professor",key = "#id")
    public void deleteProfessor(Long id) {

    Professor professor = getProfessorEntityById(id);

        if (professor.getUser() != null) {
            professor.getUser().setProfessor(null);
            professor.setUser(null);
        }

        professorRepository.deleteById(id);
    }

    public Professor getProfessorByUsername(String username) {
        return professorRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Professor not found for username: " + username));
    }
}
