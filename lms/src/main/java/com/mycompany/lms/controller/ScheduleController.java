package com.mycompany.lms.controller;

import com.mycompany.lms.dto.ScheduleDto;
import com.mycompany.lms.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping("/{id}")
    public ScheduleDto getById(@PathVariable Long id) {
        return scheduleService.getById(id);
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

    @GetMapping("/group/{groupId}")
    public List<ScheduleDto> groupSchedule(@PathVariable Long groupId) {
        return scheduleService.getGroupSchedule(groupId);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ScheduleDto> teacherSchedule(@PathVariable Long teacherId) {
        return scheduleService.getTeacherSchedule(teacherId);
    }
}

