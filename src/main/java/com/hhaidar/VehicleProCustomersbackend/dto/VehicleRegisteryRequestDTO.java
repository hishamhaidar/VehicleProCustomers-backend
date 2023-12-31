package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRegisteryRequestDTO {
    private String brand;
    private String model;
    private String yearOfProduction;
}
