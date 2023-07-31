package com.github.bernabaris.gradesystemspringboot.repository;

import com.github.bernabaris.gradesystemspringboot.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
