package com.mycompany.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupCourseDto {

    @NotNull
    private Long groupId;

    @NotNull
    private Long courseId;
}