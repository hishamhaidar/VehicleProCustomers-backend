package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrartionRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
