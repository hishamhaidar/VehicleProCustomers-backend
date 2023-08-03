package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface GarageCustomerServices {
    ResponseEntity<String> registerCustomer(CustomerRegistrartionRequestDTO registrartionRequest);
    ResponseEntity<AuthenticationResponseDTO> authenticate(AuthRequestDTO authRequest);
    ResponseEntity<String> modifyAccountData(String email,CustomerRegistrartionRequestDTO registrartionRequest);
}
