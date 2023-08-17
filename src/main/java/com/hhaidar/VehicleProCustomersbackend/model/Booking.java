package com.hhaidar.VehicleProCustomersbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private Integer bookingID;
    private Integer slotID;
    private Integer customerID;
    private Integer vehicleID;
    private String bookingStatus;
    private String vehicleProblem;


}
