package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequest;
import com.hhaidar.VehicleProCustomersbackend.exceptions.UserExists;
import com.hhaidar.VehicleProCustomersbackend.model.Customers;
import com.hhaidar.VehicleProCustomersbackend.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServices {
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    public ResponseEntity<String> registerCustomer(CustomerRegistrartionRequest registrartionRequest) {
        Optional<Customers> customerExist = customerRepo.findUserByCustomerEmail(registrartionRequest.getCustomerEmail());
        if (customerExist.isPresent())
            return  ResponseEntity.badRequest().body(new UserExists("CUSTOMER EMAIL ALREADY EXIST").toString());
        Customers newCustomer = Customers.builder()
                .firstName(registrartionRequest.getFirstName())
                .lastName(registrartionRequest.getLastName())
                .customerEmail(registrartionRequest.getCustomerEmail())
                .customerEmailPassword(passwordEncoder.encode(registrartionRequest.getCustomerEmailPassword()))
                .build();
        customerRepo.save(newCustomer);
        return ResponseEntity.ok(newCustomer.toString() + " was succesfully registered");
    }
}
