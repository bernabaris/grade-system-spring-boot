package com.github.bernabaris.gradesystemspringboot.controller;

import com.github.bernabaris.gradesystemspringboot.entity.Course;
import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.service.CourseService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping("/courses")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        return ResponseEntity.ok(courseService.updateCourse(id, courseDetails));
    }
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{courseId}/students")
    public ResponseEntity<List<Student>> getStudentsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getStudentsForCourse(courseId));
    }

    @GetMapping("/{courseId}/passingStudents")
    public ResponseEntity<List<Student>> getPassingStudentsForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getPassingStudentsForCourse(courseId));
    }

    @GetMapping("/{courseId}/averageGrade")
    public ResponseEntity<Double> getAverageGradeForCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseService.getAverageGradeForCourse(courseId));
    }

}
