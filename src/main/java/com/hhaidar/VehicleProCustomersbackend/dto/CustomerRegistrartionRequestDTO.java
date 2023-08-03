package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class CustomerRegistrartionRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
