package com.github.bernabaris.gradesystemspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .name("John")
                .build();
    }

    @Test
    public void givenStudentObject_whenCreateStudent_thenReturnSavedStudent() throws Exception {
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStudents_whenGetAllStudents_thenReturnAllStudents() throws Exception {
        List<Student> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStudentId_whenGetStudentById_thenReturnStudent() throws Exception {
        when(studentService.getStudentById(any(Long.class))).thenReturn(student);

        mockMvc.perform(get("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStudentId_whenDeleteStudent_thenReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStudentIdAndUpdatedInfo_whenUpdateStudent_thenReturnUpdatedStudent() throws Exception {
        Student updatedStudent = Student.builder().id(1L).name("Updated John").build();
        when(studentService.updateStudent(any(Long.class), any(Student.class))).thenReturn(updatedStudent);

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk());
    }

    @Test
    public void givenStudentId_whenGetAverageGradeForStudent_thenReturnAverageGrade() throws Exception {
        Double averageGrade = 4.0;
        when(studentService.getAverageGradeForStudent(any(Long.class))).thenReturn(averageGrade);

        mockMvc.perform(get("/api/{studentId}/averageGrade", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
