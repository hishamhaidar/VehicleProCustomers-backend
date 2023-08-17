package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingRequestDTO {
    private Integer bookingID;
    private Integer slotID;
    private String clientFullName;
    private String clientEmail;

    private String bookingStatus;
    private Integer vehicleID;
    private String vehicleProblem;
}
