package com.mycompany.lms.mapper;

import com.mycompany.lms.dto.GroupDto;
import com.mycompany.lms.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto toDto(Group group);
    Group toEntity(GroupDto dto);
}