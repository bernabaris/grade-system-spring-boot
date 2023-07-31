package com.github.bernabaris.gradesystemspringboot.service;

import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            return student.get();
        } else {
            throw new RuntimeException("Student not found for id : " + id);
        }
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            Student student = optionalStudent.get();
            student.setName(studentDetails.getName());
            return studentRepository.save(student);
        } else {
            throw new RuntimeException("Student not found for id : " + id);
        }
    }
    public void deleteStudent(Long id) {
        if(studentRepository.findById(id).isPresent()){
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found for id : " + id);
        }
    }

    public double getAverageGradeForStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            if (student.getGrades().isEmpty()) {
                throw new RuntimeException("No grades found for student id : " + studentId);
            }
            return student.getGrades().values().stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElseThrow(() -> new RuntimeException("Error calculating average for student id : " + studentId));
        } else {
            throw new RuntimeException("Student not found for id : " + studentId);
        }
    }



}
