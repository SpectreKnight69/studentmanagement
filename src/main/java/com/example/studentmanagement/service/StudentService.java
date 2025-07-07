package com.example.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import com.example.studentmanagement.mapper.StudentMapper;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.StudentRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents(){
        return studentRepository.findAll()
            .stream()
            .map(StudentMapper::toDTO)
            .collect(Collectors.toList());
    }

    @Cacheable(value = "students", key = "#id")
    public StudentDTO getStudentById(Long id) {
        System.out.println("fetched from db");
        Student student =  studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
                return StudentMapper.toDTO(student);
    
    }

    public Student getStudentEntityById(Long id){
        Optional<Student> student =  studentRepository.findById(id);
        return student.get();
    }

    public StudentDTO createStudent(StudentDTO dto){
        Student student = StudentMapper.toEntity(dto);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    @CacheEvict(value = "student", key = "#id")
    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setCourse(dto.getCourse());
        return StudentMapper.toDTO(studentRepository.save(student));
    }      

    @CacheEvict(value = "student",key = "#id")
    public void deleteStudent(Long Id){
         Student student = getStudentEntityById(Id);

    if (student.getUser() != null) {
        student.getUser().setStudent(null);
        student.setUser(null);
    }

    studentRepository.delete(student);
    }

}
