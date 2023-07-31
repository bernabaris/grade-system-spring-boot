package com.github.bernabaris.gradesystemspringboot.service;

import com.github.bernabaris.gradesystemspringboot.entity.Teacher;
import com.github.bernabaris.gradesystemspringboot.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {

        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()){
            return teacher.get();
        } else {
            throw new RuntimeException("Teacher not found for id : " + id);
        }
    }

    public Teacher createTeacher(Teacher teacher) {

        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(optionalTeacher.isPresent()){
            Teacher teacher = optionalTeacher.get();
            teacher.setName(teacherDetails.getName());
            // Update other fields if they exist...
            return teacherRepository.save(teacher);
        } else {
            throw new RuntimeException("Teacher not found for id : " + id);
        }
    }

    public void deleteTeacher(Long id) {
        if(teacherRepository.findById(id).isPresent()){
            teacherRepository.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found for id : " + id);
        }
    }
}
