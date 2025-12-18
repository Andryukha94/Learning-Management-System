package com.mycompany.lms.service;

import com.mycompany.lms.dao.CourseRepository;
import com.mycompany.lms.dao.GroupCourseRepository;
import com.mycompany.lms.dao.GroupRepository;
import com.mycompany.lms.dto.GroupCourseDto;
import com.mycompany.lms.exception.NotFoundException;
import com.mycompany.lms.model.relation.GroupCourse;
import com.mycompany.lms.model.relation.GroupCourseId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupCourseService {

    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final GroupCourseRepository groupCourseRepository;

    @Transactional
    public void add(GroupCourseDto dto) {
        groupRepository.findById(dto.getGroupId()).orElseThrow(NotFoundException::new);
        courseRepository.findById(dto.getCourseId()).orElseThrow(NotFoundException::new);

        GroupCourseId id = new GroupCourseId(dto.getGroupId(), dto.getCourseId());
        if (groupCourseRepository.existsById(id)) return;

        GroupCourse link = new GroupCourse();
        link.setId(id);
        groupCourseRepository.save(link);
    }

    @Transactional
    public void remove(Long groupId, Long courseId) {
        groupCourseRepository.deleteById(new GroupCourseId(groupId, courseId));
    }
}