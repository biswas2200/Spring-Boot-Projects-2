package com.mappings.flightserviceexample;

import com.mappings.flightserviceexample.dto.BookingAcknowledgement;
import com.mappings.flightserviceexample.dto.FlightBookingRequest;
import com.mappings.flightserviceexample.service.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class FlightServiceExampleApplication {

    @Autowired
    private FlightBookingService flightBookingService;

    @PostMapping("/bookFlight")
    public BookingAcknowledgement flightBooking (@RequestBody FlightBookingRequest request){
        return flightBookingService.bookFlightTicket(request);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightServiceExampleApplication.class, args);
    }

}
