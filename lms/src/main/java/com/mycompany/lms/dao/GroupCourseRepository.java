package com.mycompany.lms.dao;

import com.mycompany.lms.model.relation.GroupCourse;
import com.mycompany.lms.model.relation.GroupCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupCourseRepository extends JpaRepository<GroupCourse, GroupCourseId> {
}
