package com.hhaidar.VehicleProCustomersbackend.controller;

import com.hhaidar.VehicleProCustomersbackend.model.Booking;
import com.hhaidar.VehicleProCustomersbackend.service.BookingSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class CustomerBookingsController {
    private final BookingSavingService bookingService;
    @GetMapping("/{customerID}")
    public ResponseEntity<ArrayList<Booking>> getUserBookings(@PathVariable Integer customerID){
        return bookingService.getMyBookings(customerID);
    }
}
