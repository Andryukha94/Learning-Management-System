package com.mycompany.lms.dao;

import com.mycompany.lms.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    Page<Schedule> findByGroupIdOrderByLessonDateTimeAsc(Long groupId, Pageable pageable);

    Page<Schedule> findByCourseTeacherIdOrderByLessonDateTimeAsc(Long teacherId, Pageable pageable);

    @Modifying
    @Query("delete from Schedule s where s.lessonDateTime < :threshold")
    int deleteOlderThan(@Param("threshold") LocalDateTime threshold);
}