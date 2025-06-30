package com.example.studentmanagement.mapper;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.model.Student;


public class StudentMapper {
    
     public static StudentDTO toDTO(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getCourse()
        );
    }

    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId()); 
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        return student;
    }

}
