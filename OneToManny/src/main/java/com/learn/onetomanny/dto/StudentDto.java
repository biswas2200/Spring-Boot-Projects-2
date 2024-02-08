package com.learn.onetomanny.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String studentName;
    private Integer age;
    private List<AddressesDto> addressesDtos;
}
