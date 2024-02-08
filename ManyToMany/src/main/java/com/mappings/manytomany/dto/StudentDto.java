package com.mappings.manytomany.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String sName;
    private List<Long> courseId;
}
