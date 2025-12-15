package com.mycompany.lms.dao;

import com.mycompany.lms.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByGroupIdOrderByLessonDateTimeAsc(Long groupId);

    List<Schedule> findByCourseTeacherIdOrderByLessonDateTimeAsc(Long teacherId);
}