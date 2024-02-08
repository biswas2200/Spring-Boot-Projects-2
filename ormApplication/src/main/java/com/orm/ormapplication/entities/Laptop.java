package com.orm.ormapplication.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "jpa_laptop")
public class Laptop {
    @Id
    private Integer laptopId;
    private String modelNumber;
    private String brandName;

    @OneToOne
    private Student student;
}