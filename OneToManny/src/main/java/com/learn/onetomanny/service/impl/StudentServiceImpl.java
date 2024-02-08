package com.learn.onetomanny.service.impl;

import com.learn.onetomanny.dto.AddressesDto;
import com.learn.onetomanny.dto.StudentDto;
import com.learn.onetomanny.entity.Addresses;
import com.learn.onetomanny.entity.Student;
import com.learn.onetomanny.repository.AddressRepository;
import com.learn.onetomanny.repository.StudentRepository;
import com.learn.onetomanny.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository;
    AddressRepository addressRepository;

    public StudentServiceImpl(StudentRepository studentRepository, AddressRepository addressRepository) {
        this.studentRepository = studentRepository;
        this.addressRepository = addressRepository;
    }


    private List<AddressesDto> mapAddressToDto(List<Addresses> addressesList) {
        List<AddressesDto> addressesDtoList = new ArrayList<>();
        for (Addresses addresses : addressesList) {
            AddressesDto addressesDto = new AddressesDto();
            addressesDto.setAddressId(addresses.getAddressId());
            addressesDto.setCity(addresses.getCity());
            addressesDto.setState(addresses.getState());
            addressesDto.setCountry(addresses.getCountry());
            addressesDtoList.add(addressesDto);
        }
        return addressesDtoList;
    }

    private List<Addresses> mapAddressToEntity(List<AddressesDto> addressesDtosList) {
        List<Addresses> addressesList = new ArrayList<>();
        for (AddressesDto addressesDto : addressesDtosList) {
            Addresses addresses = new Addresses();
            addresses.setAddressId(addressesDto.getAddressId());
            addresses.setCity(addressesDto.getCity());
            addresses.setState(addressesDto.getState());
            addresses.setCountry(addressesDto.getCountry());
            addressesList.add(addresses);

        }
        return addressesList;
    }

    private StudentDto mapToDto(Student student) {
        if (student == null)
            return null;

        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setStudentName(student.getStudentName());
        studentDto.setAge(student.getAge());

        List<Addresses> addresses = student.getAddresses();
        if (addresses != null) {
            List<AddressesDto> addressesDto = mapAddressToDto(addresses);
            studentDto.setAddressesDtos(addressesDto);
        }
        return studentDto;
    }

    private Student mapToEntity(StudentDto studentDto) {
        if (studentDto == null)
            return null;
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setStudentName(studentDto.getStudentName());
        student.setAge(studentDto.getAge());

        List<AddressesDto> addressesDtoList = studentDto.getAddressesDtos();
        if (addressesDtoList != null) {
            List<Addresses> addresses = mapAddressToEntity(addressesDtoList);
            student.setAddresses(addresses);
        }
        return student;
    }





    @Override
    public List<StudentDto> getAllStudent() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }



    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElse(new Student());
        return mapToDto(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setStudentName(studentDto.getStudentName());
        student.setAge(studentDto.getAge());

        List<AddressesDto> addressesDtoList = studentDto.getAddressesDtos();
        if (addressesDtoList != null && !addressesDtoList.isEmpty()) {
            List<Addresses> addressesList = mapAddressToEntity(addressesDtoList);
            student.setAddresses(addressesList);
        }

        Student savedStudent = studentRepository.save(student);
        return mapToDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id).orElseThrow();

            existingStudent.setStudentName(studentDto.getStudentName());
            existingStudent.setAge(studentDto.getAge());

            List<AddressesDto> addressesDtoList = studentDto.getAddressesDtos();
            if (addressesDtoList != null && !addressesDtoList.isEmpty()) {
                List<Addresses> addresses = mapAddressToEntity(addressesDtoList);
                existingStudent.setAddresses(addresses);
            }
            Student updateStudent = studentRepository.save(existingStudent);
            return mapToDto(updateStudent);

    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
