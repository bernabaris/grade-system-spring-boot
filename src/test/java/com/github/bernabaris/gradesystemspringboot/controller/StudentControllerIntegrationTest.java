package com.github.bernabaris.gradesystemspringboot.controller;

import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllStudentsTest() throws Exception {
        Student student = new Student();
        student.setName("Taylor");
        List<Student> students = Arrays.asList(student);
        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(student.getName())));
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        Student student = new Student();
        student.setName("Taylor");
        when(studentService.getStudentById(any(Long.class))).thenReturn(student);

        mockMvc.perform(get("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(student.getName())));
    }

    @Test
    public void createStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Taylor");
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(student.getName())));
    }

    @Test
    public void updateStudentTest() throws Exception {
        Student student = new Student();
        student.setName("Taylor Updated");
        when(studentService.updateStudent(any(Long.class), any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(student.getName())));
    }

    @Test
    public void deleteStudentTest() throws Exception {
        mockMvc.perform(delete("/api/students/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAverageGradeForStudentTest() throws Exception {
        Double averageGrade = 4.0;
        when(studentService.getAverageGradeForStudent(any(Long.class))).thenReturn(averageGrade);

        mockMvc.perform(get("/api/1/averageGrade")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(averageGrade)));
    }
}
