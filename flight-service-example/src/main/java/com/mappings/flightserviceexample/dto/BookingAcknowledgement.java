package com.mappings.flightserviceexample.dto;

import com.mappings.flightserviceexample.entity.PassengerInfo;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingAcknowledgement {
    private String status;
    private Double totalFare;
    private String pnr;
    private PassengerInfo passengerInfo;

}
