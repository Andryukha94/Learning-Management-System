package com.mycompany.lms.controller;

import com.mycompany.lms.dto.StudentDto;
import com.mycompany.lms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public Page<StudentDto> getAll(@PageableDefault(size = 20) Pageable pageable) {
        return studentService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public StudentDto getById(@PathVariable Long id) {
        return studentService.getById(id);
    }

    @PostMapping
    public StudentDto create(@Valid @RequestBody StudentDto dto) {
        return studentService.create(dto);
    }

    @PutMapping
    public StudentDto update(@Valid @RequestBody StudentDto dto) {
        return studentService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}