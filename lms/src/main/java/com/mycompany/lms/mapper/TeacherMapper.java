package com.mycompany.lms.mapper;

import com.mycompany.lms.dto.TeacherDto;
import com.mycompany.lms.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toDto(Teacher teacher);
    Teacher toEntity(TeacherDto dto);
}