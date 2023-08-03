package com.hhaidar.VehicleProCustomersbackend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponseDTO {
    private String jwtToken;
}
