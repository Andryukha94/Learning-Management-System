package com.mycompany.lms.mapper;

import com.mycompany.lms.dto.ScheduleDto;
import com.mycompany.lms.model.Course;
import com.mycompany.lms.model.Group;
import com.mycompany.lms.model.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "course.id", target = "courseId")
    ScheduleDto toDto(Schedule schedule);

    @Mapping(target = "group", expression = "java(groupFromId(dto.getGroupId()))")
    @Mapping(target = "course", expression = "java(courseFromId(dto.getCourseId()))")
    Schedule toEntity(ScheduleDto dto);

    default Group groupFromId(Long id) {
        if (id == null) return null;
        Group g = new Group();
        g.setId(id);
        return g;
    }

    default Course courseFromId(Long id) {
        if (id == null) return null;
        Course c = new Course();
        c.setId(id);
        return c;
    }
}