package com.github.bernabaris.gradesystemspringboot.service;

import com.github.bernabaris.gradesystemspringboot.entity.Course;
import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if(course.isPresent()){
            return course.get();
        } else {
            throw new RuntimeException("Course not found for id : " + id);
        }
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Update a course
    public Course updateCourse(Long id, Course courseDetails) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            Course course = optionalCourse.get();
            course.setName(courseDetails.getName());
            // Update other fields if they exist...
            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found for id : " + id);
        }
    }

    // Delete a course
    public void deleteCourse(Long id) {
        if(courseRepository.findById(id).isPresent()){
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course not found for id : " + id);
        }
    }

    public List<Student> getStudentsForCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()){
            return new ArrayList<>(optionalCourse.get().getStudents());
        } else {
            throw new RuntimeException("Course not found for id : " + courseId);
        }
    }

    public List<Student> getPassingStudentsForCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()){
            return optionalCourse.get().getStudents().stream()
                    .filter(student -> student.getGrades().get(optionalCourse.get()) >= 50)
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Course not found for id : " + courseId);
        }
    }

    public double getAverageGradeForCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if (optionalCourse.isPresent()){
            return optionalCourse.get().getGrades().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElseThrow(() -> new RuntimeException("No grades found for course id : " + courseId));
        } else {
            throw new RuntimeException("Course not found for id : " + courseId);
        }
    }
}
