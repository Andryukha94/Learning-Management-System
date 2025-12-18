package com.mycompany.lms.dao;

import com.mycompany.lms.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findByGroupIdOrderByLessonDateTimeAsc(Long groupId, Pageable pageable);

    Page<Schedule> findByCourseTeacherIdOrderByLessonDateTimeAsc(Long teacherId, Pageable pageable);
}