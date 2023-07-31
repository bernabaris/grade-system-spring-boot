package com.github.bernabaris.gradesystemspringboot.controller;

import com.github.bernabaris.gradesystemspringboot.entity.Student;
import com.github.bernabaris.gradesystemspringboot.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllStudents() {
        // Arrange
        Student student1 = new Student(1L, "Taylor", null, null);
        Student student2 = new Student(2L, "Lautner", null, null);
        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));

        // Act
        ResponseEntity<Student[]> response = restTemplate.getForEntity("/api/students", Student[].class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(2);
        assertThat(response.getBody()[0].getName()).isEqualTo(student1.getName());
        assertThat(response.getBody()[1].getName()).isEqualTo(student2.getName());
    }

    @Test
    public void testGetStudentById() {
        // Arrange
        Long studentId = 1L;
        Student student = new Student(studentId, "Taylor", null, null);
        when(studentService.getStudentById(studentId)).thenReturn(student);

        // Act
        ResponseEntity<Student> response = restTemplate.getForEntity("/api/students/" + studentId, Student.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(student.getName());
    }





    @Test
    public void testGetAverageGradeForStudent() {
        // Arrange
        Long studentId = 1L;
        double averageGrade = 88.5;
        when(studentService.getAverageGradeForStudent(studentId)).thenReturn(averageGrade);

        // Act
        ResponseEntity<Double> response = restTemplate.getForEntity("/api/" + studentId + "/averageGrade", Double.class);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(averageGrade);
    }
}
