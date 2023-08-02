package com.hhaidar.VehicleProCustomersbackend.controller;

import com.hhaidar.VehicleProCustomersbackend.dto.AuthRequest;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthenticationResponse;
import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequest;
import com.hhaidar.VehicleProCustomersbackend.service.CustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServices customerServices;

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody CustomerRegistrartionRequest registrartionRequest){
        return customerServices.registerCustomer(registrartionRequest);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(
            @RequestBody AuthRequest authRequestt) {
        return customerServices.authenticate(authRequestt);


    }
}
