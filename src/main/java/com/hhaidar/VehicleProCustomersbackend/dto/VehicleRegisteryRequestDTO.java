package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class VehicleRegisteryRequestDTO {
    private String brand;
    private String model;
    private String yearOfProduction;
}
