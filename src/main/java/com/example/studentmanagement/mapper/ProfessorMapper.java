package com.example.studentmanagement.mapper;

import com.example.studentmanagement.dto.ProfessorDTO;
import com.example.studentmanagement.model.Professor;

public class ProfessorMapper {
    
     public static ProfessorDTO toDTO(Professor professor) {
        return new ProfessorDTO(
                professor.getId(),
                professor.getName(),
                professor.getEmail(),
                professor.getDepartment()
        );
    }

    public static Professor toEntity(ProfessorDTO dto) {
        Professor professor = new Professor();
        professor.setId(dto.getId()); 
        professor.setName(dto.getName());
        professor.setEmail(dto.getEmail());
        professor.setDepartment(dto.getDepartment());
        return professor;
    }


}
