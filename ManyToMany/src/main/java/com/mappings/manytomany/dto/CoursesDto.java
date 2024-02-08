package com.mappings.manytomany.dto;

import lombok.Data;

import java.util.List;

@Data
public class CoursesDto {
    private Long id;
    private String title;
    private List<Long> studentId;
}
