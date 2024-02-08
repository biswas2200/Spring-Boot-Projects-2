package com.learn.onetomanny.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;
    private String empName;
    @OneToOne(mappedBy = "employee")
    private Department department;
}
