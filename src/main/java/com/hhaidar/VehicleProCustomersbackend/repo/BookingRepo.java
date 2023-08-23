package com.hhaidar.VehicleProCustomersbackend.repo;

import com.hhaidar.VehicleProCustomersbackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Integer> {
    Optional<Booking> findByBookingID(Integer bookingID);

    Optional<ArrayList<Booking>> findByCustomerID(Integer customerID);
}
