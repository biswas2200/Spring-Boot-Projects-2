package com.learn.onetomanny.service.impl;

import com.learn.onetomanny.dto.DepartmentDto;
import com.learn.onetomanny.dto.EmployeeDto;
import com.learn.onetomanny.entity.Department;
import com.learn.onetomanny.entity.Employee;
import com.learn.onetomanny.repository.DepartmentRepository;
import com.learn.onetomanny.repository.EmployeeRepository;
import com.learn.onetomanny.service.EmployeeImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeServiceImpl implements EmployeeImpl {

    EmployeeRepository employeeRepository;
    DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    private DepartmentDto mapDeptTODto(Department department) {
        if (department == null)
            return null;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDeptName(department.getDeptName());
        return departmentDto;
    }

    private EmployeeDto mapToDto(Employee employee) {
        if (employee == null)
            return null;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmpName(employeeDto.getEmpName());

        Department department = employee.getDepartment();
        if (department != null) {
            DepartmentDto departmentDto = mapDeptTODto(department);
            employeeDto.setDepartmentDto(departmentDto);
        }
        return employeeDto;
    }

    private Department mapDtoToDept(DepartmentDto departmentDto) {
        if (departmentDto == null)
            return null;
        Department department = new Department();
        department.setDeptName(departmentDto.getDeptName());
        return department;
    }

    private Employee mapToEntity(EmployeeDto employeeDto) {
        if (employeeDto == null)
            return null;
        Employee employee = new Employee();
        employee.setEmpName(employeeDto.getEmpName());

        DepartmentDto departmentDto = employeeDto.getDepartmentDto();
        if (departmentDto != null) {
            Department department = mapDtoToDept(departmentDto);
            employee.setDepartment(department);
        }
        return employee;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeById(Long empId) {
        Employee employee = employeeRepository.findById(empId)
                .orElse(new Employee());
        return mapToDto(employee);
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return mapToDto(savedEmployee);
    }

    @Override
    public EmployeeDto updateEmployee(Long empId, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setEmpName(employeeDto.getEmpName());

        DepartmentDto departmentDto = employeeDto.getDepartmentDto();
        if (departmentDto != null) {
            Department department = mapDtoToDept(departmentDto);
            existingEmployee.setDepartment(department);
        }

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return mapToDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }
}
