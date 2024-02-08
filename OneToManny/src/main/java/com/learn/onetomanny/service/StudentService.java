package com.learn.onetomanny.service;

import com.learn.onetomanny.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudent();

    StudentDto getStudentById(Long id);

    StudentDto createStudent(StudentDto studentDto);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    void deleteStudentById(Long id);

}
