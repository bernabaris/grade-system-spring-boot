package com.github.bernabaris.gradesystemspringboot.controller;

import com.github.bernabaris.gradesystemspringboot.entity.Teacher;
import com.github.bernabaris.gradesystemspringboot.service.TeacherService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class TeacherController {
        private TeacherService teacherService;
        public TeacherController(TeacherService teacherService){
            this.teacherService=teacherService;
        }

        @GetMapping("/teachers")
        public ResponseEntity<List<Teacher>> getAllTeachers() {
            return ResponseEntity.ok(teacherService.getAllTeachers());
        }

        @GetMapping("/teachers/{id}")
        public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id){
            return ResponseEntity.ok(teacherService.getTeacherById(id));
        }

        @PostMapping("/teachers")
        public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
            return ResponseEntity.ok(teacherService.createTeacher(teacher));
        }

        @PutMapping("/teachers/{id}")
        public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
            return ResponseEntity.ok(teacherService.updateTeacher(id, teacherDetails));
        }
        @DeleteMapping("/teachers/{id}")
        public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok().build();
        }
}
