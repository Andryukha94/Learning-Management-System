package com.mycompany.lms.service;

import com.mycompany.lms.dao.TeacherRepository;
import com.mycompany.lms.dto.TeacherDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.mapper.TeacherMapper;
import com.mycompany.lms.model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public List<TeacherDto> getAll() {
        return teacherRepository.findAll().stream().map(teacherMapper::toDto).toList();
    }

    public TeacherDto getById(Long id) {
        return teacherMapper.toDto(
                teacherRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Transactional
    public TeacherDto create(TeacherDto dto) {
        Teacher teacher = teacherMapper.toEntity(dto);
        teacher.setId(null);
        return teacherMapper.toDto(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherDto update(TeacherDto dto) {
        if (dto.getId() == null) throw new IllegalArgumentException();

        Teacher existing = teacherRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());

        return teacherMapper.toDto(teacherRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        if (!teacherRepository.existsById(id)) throw new NotFoundException();
        teacherRepository.deleteById(id);
    }
}