package com.mycompany.lms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;
}
