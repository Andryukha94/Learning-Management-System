package com.mycompany.lms.controller;

import com.mycompany.lms.dto.StudentDto;
import com.mycompany.lms.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentDto> getAll() {
        return studentService.getAll();
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



