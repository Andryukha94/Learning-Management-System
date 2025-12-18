package com.mycompany.lms.service;

import com.mycompany.lms.dao.GroupRepository;
import com.mycompany.lms.dao.StudentRepository;
import com.mycompany.lms.dto.StudentDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.mapper.StudentMapper;
import com.mycompany.lms.model.Group;
import com.mycompany.lms.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    public Page<StudentDto> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable).map(studentMapper::toDto);
    }

    public StudentDto getById(Long id) {
        return studentMapper.toDto(
                studentRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Transactional
    public StudentDto create(StudentDto dto) {

        Group group = groupRepository.findById(dto.getGroupId())
                .orElseThrow(NotFoundException::new);

        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setGroup(group);

        return studentMapper.toDto(studentRepository.save(student));
    }

    @Transactional
    public StudentDto update(StudentDto dto) {
        if (dto.getId() == null) throw new IllegalArgumentException();

        Student existing = studentRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        Group group = groupRepository.findById(dto.getGroupId()).orElseThrow(NotFoundException::new);

        existing.setFirstName(dto.getFirstName());
        existing.setLastName(dto.getLastName());
        existing.setGroup(group);

        return studentMapper.toDto(studentRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        if (!studentRepository.existsById(id)) throw new NotFoundException();
        studentRepository.deleteById(id);
    }
}