package com.learn.onetomanny.controller;

import com.learn.onetomanny.dto.StudentDto;
import com.learn.onetomanny.entity.Student;
import com.learn.onetomanny.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/get")
    public List<StudentDto> getAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/get/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/create")
    public StudentDto createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @PutMapping("/get/{id}")
    public StudentDto updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(id, studentDto);
    }

    @DeleteMapping("/get/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }

}
