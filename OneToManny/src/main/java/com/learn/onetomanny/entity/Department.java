package com.learn.onetomanny.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;
    private String deptName;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
