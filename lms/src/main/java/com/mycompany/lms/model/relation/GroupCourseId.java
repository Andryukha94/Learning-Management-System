package com.mycompany.lms.model.relation;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupCourseId implements Serializable {

    private Long groupId;
    private Long courseId;
}