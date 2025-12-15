package com.mycompany.lms.service;

import com.mycompany.lms.dao.CourseRepository;
import com.mycompany.lms.dao.TeacherRepository;
import com.mycompany.lms.dto.CourseDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.mapper.CourseMapper;
import com.mycompany.lms.model.Course;
import com.mycompany.lms.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapper courseMapper;

    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }

    public CourseDto getById(Long id) {
        return courseMapper.toDto(
                courseRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Transactional
    public CourseDto create(CourseDto dto) {
        teacherRepository.findById(dto.getTeacherId()).orElseThrow(NotFoundException::new);

        Course course = courseMapper.toEntity(dto);
        course.setId(null);

        return courseMapper.toDto(courseRepository.save(course));
    }

    @Transactional
    public CourseDto update(CourseDto dto) {
        if (dto.getId() == null) throw new IllegalArgumentException();

        Course existing = courseRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        Teacher teacher = teacherRepository.findById(dto.getTeacherId()).orElseThrow(NotFoundException::new);

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setTeacher(teacher);

        return courseMapper.toDto(courseRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        if (!courseRepository.existsById(id)) throw new NotFoundException();
        courseRepository.deleteById(id);
    }
}
