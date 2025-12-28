package com.mycompany.lms.controller;

import com.mycompany.lms.dto.ScheduleDto;
import com.mycompany.lms.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ScheduleDto getById(@PathVariable Long id) {
        return scheduleService.getById(id);
    }

    @GetMapping("/group/{groupId}")
    public Page<ScheduleDto> groupSchedule(@PathVariable Long groupId, Pageable pageable) {
        return scheduleService.getGroupSchedule(groupId, pageable);
    }

    @GetMapping("/teacher/{teacherId}")
    public Page<ScheduleDto> teacherSchedule(@PathVariable Long teacherId, Pageable pageable) {
        return scheduleService.getTeacherSchedule(teacherId, pageable);
    }

    @PostMapping
    public ScheduleDto create(@Valid @RequestBody ScheduleDto dto) {
        return scheduleService.create(dto);
    }

    @PutMapping
    public ScheduleDto update(@Valid @RequestBody ScheduleDto dto) {
        return scheduleService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        scheduleService.delete(id);
    }
}