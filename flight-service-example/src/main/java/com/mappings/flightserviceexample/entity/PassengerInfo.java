package com.mappings.flightserviceexample.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "passenger_info_jpa")
public class PassengerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pId;
    private String pName;
    private String pEmail;
    private String source;
    private String destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy")
    private Date travelDate;
    private String pickUpTime;
    private String arrivalTime;
    private Double fare;
}
