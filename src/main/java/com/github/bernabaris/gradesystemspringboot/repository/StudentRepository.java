package com.github.bernabaris.gradesystemspringboot.repository;

import com.github.bernabaris.gradesystemspringboot.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
