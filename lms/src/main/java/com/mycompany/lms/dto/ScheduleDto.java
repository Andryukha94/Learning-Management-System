package com.mycompany.lms.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {

    private Long id;

    @NotNull
    private Long groupId;

    @NotNull
    private Long courseId;

    @NotNull
    private LocalDateTime lessonDateTime;
}