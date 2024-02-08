package com.learn.onetomanny.controller;

import com.learn.onetomanny.dto.EmployeeDto;
import com.learn.onetomanny.service.EmployeeImpl;
import com.learn.onetomanny.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeServiceImpl employeeImpl;

    @Autowired
    public EmployeeController(EmployeeServiceImpl employeeImpl){
        this.employeeImpl = employeeImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto createdEmployee = employeeImpl.createEmployee(employeeDto);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long empId, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updatedEmployee = employeeImpl.updateEmployee(empId, employeeDto);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long empId) {
        EmployeeDto employee = employeeImpl.getEmployeeById(empId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<EmployeeDto> getAllEmployee(){
        List<EmployeeDto> employeeDtos = employeeImpl.getAllEmployee();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{empId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long empId){
        employeeImpl.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
