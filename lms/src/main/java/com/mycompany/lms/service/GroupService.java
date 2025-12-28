package com.mycompany.lms.service;

import com.mycompany.lms.dao.GroupRepository;
import com.mycompany.lms.dto.GroupDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.mapper.GroupMapper;
import com.mycompany.lms.model.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public Page<GroupDto> getAll(Pageable pageable) {
        return groupRepository.findAll(pageable).map(groupMapper::toDto);
    }

    public GroupDto getById(Long id) {
        return groupMapper.toDto(
                groupRepository.findById(id).orElseThrow(NotFoundException::new)
        );
    }

    @Transactional
    public GroupDto create(GroupDto dto) {
        Group group = groupMapper.toEntity(dto);
        group.setId(null);
        return groupMapper.toDto(groupRepository.save(group));
    }

    @Transactional
    public GroupDto update(GroupDto dto) {
        if (dto.getId() == null) throw new IllegalArgumentException();

        Group existing = groupRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        existing.setName(dto.getName());

        return groupMapper.toDto(groupRepository.save(existing));
    }

    @Transactional
    public void delete(Long id) {
        if (!groupRepository.existsById(id)) throw new NotFoundException();
        groupRepository.deleteById(id);
    }
}