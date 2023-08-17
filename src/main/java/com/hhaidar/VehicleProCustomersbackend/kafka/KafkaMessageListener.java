package com.hhaidar.VehicleProCustomersbackend.kafka;

import com.hhaidar.VehicleProCustomersbackend.dto.BookingRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import com.hhaidar.VehicleProCustomersbackend.repo.GarageCustomersRepo;
import com.hhaidar.VehicleProCustomersbackend.service.BookingSavingService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class KafkaMessageListener {
    private final BookingSavingService savingService;
    @KafkaListener(topics = "bookings", groupId = "customer-group")
    public void listenToBookingsTopic(BookingRequestDTO bookingRequest) {
        System.out.println("Received message: " + bookingRequest);
        savingService.saveBooking(bookingRequest);



    }
}