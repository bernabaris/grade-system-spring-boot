package com.github.bernabaris.gradesystemspringboot.repository;

import com.github.bernabaris.gradesystemspringboot.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long>{
}
