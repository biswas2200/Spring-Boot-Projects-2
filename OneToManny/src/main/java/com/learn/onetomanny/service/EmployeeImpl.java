package com.learn.onetomanny.service;

import com.learn.onetomanny.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeImpl {
    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(Long empId);
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Long empId, EmployeeDto employeeDto);
    void deleteEmployee(Long empId);
}
