package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;

@Getter
@EqualsAndHashCode
@ToString
public class CustomerRegistrartionRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
