package com.mycompany.lms.model.relation;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "group_courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCourse {

    @EmbeddedId
    private GroupCourseId id;
}