package com.mycompany.lms.mapper;

import com.mycompany.lms.dto.CourseDto;
import com.mycompany.lms.model.Course;
import com.mycompany.lms.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDto toDto(Course course);

    @Mapping(target = "teacher", expression = "java(teacherFromId(dto.getTeacherId()))")
    Course toEntity(CourseDto dto);

    default Teacher teacherFromId(Long id) {
        if (id == null) return null;
        Teacher t = new Teacher();
        t.setId(id);
        return t;
    }
}