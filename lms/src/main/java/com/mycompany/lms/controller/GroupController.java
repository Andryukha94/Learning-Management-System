package com.mycompany.lms.controller;

import com.mycompany.lms.dto.GroupDto;
import com.mycompany.lms.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    public List<GroupDto> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public GroupDto getById(@PathVariable Long id) {
        return groupService.getById(id);
    }

    @PostMapping
    public GroupDto create(@Valid @RequestBody GroupDto dto) {
        return groupService.create(dto);
    }

    @PutMapping
    public GroupDto update(@Valid @RequestBody GroupDto dto) {
        return groupService.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}




