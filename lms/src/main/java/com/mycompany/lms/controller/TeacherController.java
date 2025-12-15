package com.mycompany.lms.controller;

import com.mycompany.lms.dto.TeacherDto;
import com.mycompany.lms.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherDto> getAll() {
        return teacherService.getAll();
    }

    @GetMapping("/{id}")
    public TeacherDto getById(@PathVariable Long id) {
        return teacherService.getById(id);
    }

    @PostMapping
    public TeacherDto create(@Valid @RequestBody TeacherDto dto) {
        return teacherService.create(dto);
    }

    @PutMapping
    public TeacherDto update(@Valid @RequestBody TeacherDto dto) {
        return teacherService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}

