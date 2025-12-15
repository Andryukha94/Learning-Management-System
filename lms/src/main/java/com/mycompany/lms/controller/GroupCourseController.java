package com.mycompany.lms.controller;

import com.mycompany.lms.dto.GroupCourseDto;
import com.mycompany.lms.service.GroupCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-courses")
@RequiredArgsConstructor
public class GroupCourseController {

    private final GroupCourseService groupCourseService;

    @PostMapping
    public void add(@Valid @RequestBody GroupCourseDto dto) {
        groupCourseService.add(dto);
    }

    @DeleteMapping
    public void remove(@RequestParam Long groupId, @RequestParam Long courseId) {
        groupCourseService.remove(groupId, courseId);
    }
}
