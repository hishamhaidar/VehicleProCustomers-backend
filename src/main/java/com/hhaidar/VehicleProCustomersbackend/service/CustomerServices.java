package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.AuthRequest;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthenticationResponse;
import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequest;
import com.hhaidar.VehicleProCustomersbackend.exceptions.UserExists;
import com.hhaidar.VehicleProCustomersbackend.model.Customers;
import com.hhaidar.VehicleProCustomersbackend.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServices {
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<String> registerCustomer(CustomerRegistrartionRequest registrartionRequest) {
        Optional<Customers> customerExist = customerRepo.findUserByCustomerEmail(registrartionRequest.getEmail());
        if (customerExist.isPresent())
            return  ResponseEntity.badRequest().body(new UserExists("CUSTOMER EMAIL ALREADY EXIST").toString());
        Customers newCustomer = Customers.builder()
                .firstName(registrartionRequest.getFirstName())
                .lastName(registrartionRequest.getLastName())
                .customerEmail(registrartionRequest.getEmail())
                .customerEmailPassword(passwordEncoder.encode(registrartionRequest.getPassword()))
                .build();
        customerRepo.save(newCustomer);
        return ResponseEntity.ok(newCustomer.toString() + " was succesfully registered");
    }

    public ResponseEntity<AuthenticationResponse> authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        Optional<Customers> customer = customerRepo.findUserByCustomerEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(customer.get());
        return ResponseEntity.ok(AuthenticationResponse.builder()
                .jwtToken(jwtToken)
                .build());
    }
}
