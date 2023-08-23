package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.BookingRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.Booking;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface BookingSavingService {
    void saveBooking(BookingRequestDTO bookingRequest);

    ResponseEntity<ArrayList<Booking>> getMyBookings(Integer customerID);
}
