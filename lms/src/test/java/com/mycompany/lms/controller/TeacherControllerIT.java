package com.mycompany.lms.controller;

import com.mycompany.lms.dao.TeacherRepository;
import com.mycompany.lms.model.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TeacherControllerIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("lms_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void registerDbProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    void getById_shouldReturnTeacher() {
        Teacher t = new Teacher();
        t.setFirstName("Ivan");
        t.setLastName("Petrov");
        t = teacherRepository.save(t);

        String url = "http://localhost:" + port + "/api/v1/teachers/" + t.getId();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).contains("\"id\":" + t.getId());
        assertThat(response.getBody()).contains("\"firstName\":\"Ivan\"");
        assertThat(response.getBody()).contains("\"lastName\":\"Petrov\"");
    }
}