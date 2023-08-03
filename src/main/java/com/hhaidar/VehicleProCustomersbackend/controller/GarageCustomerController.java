package com.hhaidar.VehicleProCustomersbackend.controller;

import com.hhaidar.VehicleProCustomersbackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.service.GarageCustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class GarageCustomerController {
    private final GarageCustomerServices customerServices;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrartionRequestDTO registrartionRequest){
        return customerServices.registerCustomer(registrartionRequest);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            @RequestBody AuthRequestDTO authRequestt) {
        return customerServices.authenticate(authRequestt);
    }
    @PutMapping("/modify/{email}")
    public ResponseEntity<String> modifyCustomerAccount(@PathVariable String email,@RequestBody CustomerRegistrartionRequestDTO registrartionRequest)
    {
        return customerServices.modifyAccountData(email,registrartionRequest);
    }
}
