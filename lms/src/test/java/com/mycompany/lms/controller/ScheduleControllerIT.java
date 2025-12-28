package com.mycompany.lms.controller;

import com.mycompany.lms.AbstractIT;
import com.mycompany.lms.dao.*;
import com.mycompany.lms.model.*;
import com.mycompany.lms.model.relation.GroupCourse;
import com.mycompany.lms.model.relation.GroupCourseId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ScheduleControllerIT extends AbstractIT {

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GroupCourseRepository groupCourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    private Long groupId;
    private Long courseId;

    private LocalDateTime dt1;
    private LocalDateTime dt2;

    @BeforeEach
    void setUp() {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Ivan");
        teacher.setLastName("Petrov");
        teacher = teacherRepository.save(teacher);

        Group group = new Group();
        group.setName("Group A");
        group = groupRepository.save(group);
        groupId = group.getId();

        Course course = new Course();
        course.setName("Java");
        course.setDescription("Base Java");
        course.setTeacher(teacher);
        course = courseRepository.save(course);
        courseId = course.getId();

        GroupCourse link = new GroupCourse();
        link.setId(new GroupCourseId(groupId, courseId));
        groupCourseRepository.save(link);

        dt1 = LocalDateTime.of(2025, 12, 20, 18, 0);
        dt2 = LocalDateTime.of(2025, 12, 21, 18, 0);

        Schedule s1 = new Schedule();
        s1.setGroup(group);
        s1.setCourse(course);
        s1.setLessonDateTime(dt1);
        scheduleRepository.save(s1);

        Schedule s2 = new Schedule();
        s2.setGroup(group);
        s2.setCourse(course);
        s2.setLessonDateTime(dt2);
        scheduleRepository.save(s2);
    }

    @AfterEach
    void cleanUp() {
        scheduleRepository.deleteAll();
        groupCourseRepository.deleteAll();
        studentRepository.deleteAll();

        courseRepository.deleteAll();
        groupRepository.deleteAll();
        teacherRepository.deleteAll();
    }


    @Test
    void groupSchedule_shouldReturnFirstPage_sortedAndPaged() {
        String endpoint = "/api/v1/schedule/group/" + groupId + "?page=0&size=1";
        ResponseEntity<String> response = restTemplate.getForEntity(url(endpoint), String.class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);

        String body = response.getBody();
        assertThat(body).isNotNull();

        assertThat(body).contains("\"content\"");
        assertThat(body).contains("\"pageable\"");

        assertThat(body).contains(dt1.toString());
        assertThat(body).doesNotContain(dt2.toString());

        assertThat(body).contains("\"groupId\":" + groupId);
        assertThat(body).contains("\"courseId\":" + courseId);
    }
}
