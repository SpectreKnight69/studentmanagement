package com.example.studentmanagement.repository;

import org.springframework.stereotype.Repository;
import com.example.studentmanagement.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserUsername(String username);
}
