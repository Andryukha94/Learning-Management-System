package com.mycompany.lms.mapper;

import com.mycompany.lms.dto.StudentDto;
import com.mycompany.lms.model.Group;
import com.mycompany.lms.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "group.id", target = "groupId")
    StudentDto toDto(Student student);

    @Mapping(target = "group", expression = "java(groupFromId(dto.getGroupId()))")
    Student toEntity(StudentDto dto);

    default Group groupFromId(Long id) {
        if (id == null) return null;
        Group g = new Group();
        g.setId(id);
        return g;
    }
}
