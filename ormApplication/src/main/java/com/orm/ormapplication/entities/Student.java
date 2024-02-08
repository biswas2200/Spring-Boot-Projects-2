package com.orm.ormapplication.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "jpa_student")
public class Student {
    @Id
    private Integer studentId;
    private String studentName;
    private String about;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Laptop laptop;
}
