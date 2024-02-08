package com.learn.onetomanny.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long empId;
    private String empName;
    private DepartmentDto departmentDto;

}
