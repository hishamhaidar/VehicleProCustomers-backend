package com.hhaidar.VehicleProCustomersbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomersVehicles {
    @Id
    @GeneratedValue
    private Integer vehicleID;
    @Column(name = "owner_id")
    private Integer vehicleOwnerID;
    @Column(name = "brand")
    private String vehicleBrand;
    @Column(name = "model")
    private String vehicleModel;

    private String yearOfProduction;


}
