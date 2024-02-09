package com.mappings.flightserviceexample.service;

import com.mappings.flightserviceexample.dto.BookingAcknowledgement;
import com.mappings.flightserviceexample.dto.FlightBookingRequest;
import com.mappings.flightserviceexample.entity.PassengerInfo;
import com.mappings.flightserviceexample.entity.PaymentInfo;
import com.mappings.flightserviceexample.repository.PassengerInfoRepository;
import com.mappings.flightserviceexample.repository.PaymentInfoRepository;
import com.mappings.flightserviceexample.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FlightBookingService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;

    public BookingAcknowledgement bookFlightTicket(FlightBookingRequest request) {
        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();
        PaymentUtils.validatePayment(paymentInfo.getAccountNo(), passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getPId());
        paymentInfo.setAmount(passengerInfo.getFare());

        paymentInfoRepository.save(paymentInfo);

        return new BookingAcknowledgement("SUCCESS", passengerInfo.getFare(),
                UUID.randomUUID().toString().split("-")[0],passengerInfo);


    }
}
