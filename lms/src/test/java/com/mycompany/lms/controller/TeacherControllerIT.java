package com.mycompany.lms.controller;

import com.mycompany.lms.AbstractIT;
import com.mycompany.lms.dao.TeacherRepository;
import com.mycompany.lms.model.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.assertj.core.api.Assertions.assertThat;

class TeacherControllerIT extends AbstractIT {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Teacher savedTeacher;

    @BeforeEach
    void setUp() {
        Teacher t = new Teacher();
        t.setFirstName("Ivan");
        t.setLastName("Petrov");
        savedTeacher = teacherRepository.save(t);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("""
            TRUNCATE TABLE schedule,
                         group_courses,
                         students,
                         courses,
                         groups,
                         teachers
            RESTART IDENTITY CASCADE
        """);
    }

    @Test
    void getById_shouldReturnTeacher() {
        String endpoint = "/api/v1/teachers/" + savedTeacher.getId();

        ResponseEntity<String> response =
                restTemplate.getForEntity(url(endpoint), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("\"firstName\":\"Ivan\"");
        assertThat(response.getBody()).contains("\"lastName\":\"Petrov\"");
    }
}