package com.learn.onetomanny.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "jpa_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_name", nullable = false)
    private String studentName;
    @Column(name = "student_age", nullable = false)
    private Integer age;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    List<Addresses> addresses;
}
