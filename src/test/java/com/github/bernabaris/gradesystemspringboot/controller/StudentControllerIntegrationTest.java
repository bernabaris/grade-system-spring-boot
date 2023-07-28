package com.github.bernabaris.gradesystemspringboot.controller;

import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private MockMvc mvc;

    @Test // This test is failing   :(
    void getAllStudents() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(studentController).build();

        when(studentService.getAllStudents()).thenReturn(Arrays.asList(
                new Student(1L, "Berna Baris ",null,null),
                new Student(2L, "Beyza Baris",null,null)
        ));

        mvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Berna Baris")))
                .andExpect(jsonPath("$[1].name", is("Beyza Baris")));
    }

}