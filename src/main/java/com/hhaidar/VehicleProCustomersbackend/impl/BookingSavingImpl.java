package com.hhaidar.VehicleProCustomersbackend.impl;

import com.hhaidar.VehicleProCustomersbackend.dto.BookingRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.Booking;
import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import com.hhaidar.VehicleProCustomersbackend.repo.BookingRepo;
import com.hhaidar.VehicleProCustomersbackend.repo.GarageCustomersRepo;
import com.hhaidar.VehicleProCustomersbackend.service.BookingSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingSavingImpl implements BookingSavingService {
    private final GarageCustomersRepo customersRepo;
    private final BookingRepo bookingRepo;
    @Override
    public void saveBooking(BookingRequestDTO bookingRequest) {
        Optional<Booking> booking =bookingRepo.findByBookingID(bookingRequest.getBookingID());
        if (booking.isPresent()){
            updateBookingStatus(booking,bookingRequest.getBookingStatus());
        }
        else {
            Optional<GarageCustomers> garageCustomers = customersRepo.findByCustomerEmail(bookingRequest.getClientEmail());
            if (garageCustomers.isPresent()){
                Booking newBooking = Booking.builder()
                        .bookingID(bookingRequest.getBookingID())
                        .slotID(bookingRequest.getSlotID())
                        .customerID(garageCustomers.get().getCustomerID())
                        .vehicleID(bookingRequest.getVehicleID())
                        .vehicleProblem(bookingRequest.getVehicleProblem())
                        .bookingStatus(bookingRequest.getBookingStatus())
                        .build();
                bookingRepo.save(newBooking);
            }
            else {
                System.out.println("No user mail");
            }

        }



    }

    private void updateBookingStatus(Optional<Booking> booking, String bookingStatus) {
        Booking updatedBooking = booking.get();
        updatedBooking.setBookingStatus(bookingStatus);
        bookingRepo.save(updatedBooking);
    }
}
