package com.learn.onetomanny.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jpa_Address")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Addresses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @Column(name = "student_city", nullable = false)
    private String city;
    @Column(name = "student_state", nullable = false)
    private String state;
    @Column(name = "student_country", nullable = false)
    private String country;

    @ManyToOne
        @JoinColumn(name = "student_id")
    private Student student;
}



