package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDataModifyDTO {
    private String firstName;
    private String lastName;
    private String email;
}
