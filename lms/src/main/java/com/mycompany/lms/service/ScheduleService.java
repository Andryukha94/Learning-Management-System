package com.mycompany.lms.service;

import com.mycompany.lms.dao.CourseRepository;
import com.mycompany.lms.dao.GroupCourseRepository;
import com.mycompany.lms.dao.GroupRepository;
import com.mycompany.lms.dao.ScheduleRepository;
import com.mycompany.lms.dto.ScheduleDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.mapper.ScheduleMapper;
import com.mycompany.lms.model.Course;
import com.mycompany.lms.model.Group;
import com.mycompany.lms.model.Schedule;
import com.mycompany.lms.model.relation.GroupCourseId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final GroupCourseRepository groupCourseRepository;
    private final ScheduleMapper scheduleMapper;

    public Page<ScheduleDto> getGroupSchedule(Long groupId, Pageable pageable) {
        return scheduleRepository.findByGroupIdOrderByLessonDateTimeAsc(groupId, pageable)
                .map(scheduleMapper::toDto);
    }

    public Page<ScheduleDto> getTeacherSchedule(Long teacherId, Pageable pageable) {
        return scheduleRepository.findByCourseTeacherIdOrderByLessonDateTimeAsc(teacherId, pageable)
                .map(scheduleMapper::toDto);
    }

    public ScheduleDto getById(Long id) {
        return scheduleMapper.toDto(
                scheduleRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Transactional
    public ScheduleDto create(ScheduleDto dto) {
        groupRepository.findById(dto.getGroupId()).orElseThrow(NotFoundException::new);
        courseRepository.findById(dto.getCourseId()).orElseThrow(NotFoundException::new);

        if (!groupCourseRepository.existsById(new GroupCourseId(dto.getGroupId(), dto.getCourseId()))) {
            throw new IllegalArgumentException();
        }

        Schedule schedule = scheduleMapper.toEntity(dto);
        schedule.setId(null);

        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    @Transactional
    public ScheduleDto update(ScheduleDto dto) {
        if (dto.getId() == null) throw new IllegalArgumentException();

        Schedule existing = scheduleRepository.findById(dto.getId())
                .orElseThrow(NotFoundException::new);

        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(NotFoundException::new);

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(NotFoundException::new);

        if (!groupCourseRepository.existsById(
                new GroupCourseId(group.getId(), course.getId()))) {
            throw new IllegalArgumentException();
        }

        existing.setLessonDateTime(dto.getLessonDateTime());
        existing.setGroup(group);
        existing.setCourse(course);

        return scheduleMapper.toDto(scheduleRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) throw new NotFoundException();
        scheduleRepository.deleteById(id);
    }
}