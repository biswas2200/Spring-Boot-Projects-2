package com.mappings.flightserviceexample.dto;

import com.mappings.flightserviceexample.entity.PassengerInfo;
import com.mappings.flightserviceexample.entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingRequest {
    private PassengerInfo passengerInfo;
    private PaymentInfo paymentInfo;
}
