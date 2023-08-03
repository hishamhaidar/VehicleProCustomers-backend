package com.hhaidar.VehicleProCustomersbackend.impl;

import com.hhaidar.VehicleProCustomersbackend.config.JWTService;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.AuthenticationResponseDTO;
import com.hhaidar.VehicleProCustomersbackend.dto.CustomerRegistrartionRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.exceptions.UserExists;
import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import com.hhaidar.VehicleProCustomersbackend.repo.GarageCustomersRepo;
import com.hhaidar.VehicleProCustomersbackend.service.GarageCustomerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GarageCustomerServicesImpl implements GarageCustomerServices {
    private final GarageCustomersRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
@Override
    public ResponseEntity<String> registerCustomer(CustomerRegistrartionRequestDTO registrartionRequest) {
        Optional<GarageCustomers> customerExist = customerRepo.findUserByCustomerEmail(registrartionRequest.getEmail());
        if (customerExist.isPresent())
            return  ResponseEntity.badRequest().body(new UserExists("CUSTOMER EMAIL ALREADY EXIST").toString());
        GarageCustomers newCustomer = GarageCustomers.builder()
                .firstName(registrartionRequest.getFirstName())
                .lastName(registrartionRequest.getLastName())
                .customerEmail(registrartionRequest.getEmail())
                .customerEmailPassword(passwordEncoder.encode(registrartionRequest.getPassword()))
                .build();
        customerRepo.save(newCustomer);
        return ResponseEntity.ok(newCustomer.toString() + " was succesfully registered");
    }
@Override
    public ResponseEntity<AuthenticationResponseDTO> authenticate(AuthRequestDTO authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));
        Optional<GarageCustomers> customer = customerRepo.findUserByCustomerEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(customer.get());
        return ResponseEntity.ok(AuthenticationResponseDTO.builder()
                .jwtToken(jwtToken)
                .build());
    }

    @Override
    public ResponseEntity<String> modifyAccountData(String email,CustomerRegistrartionRequestDTO registrartionRequest){
        Optional<GarageCustomers> userByCustomerEmail = customerRepo.findUserByCustomerEmail(email);
            if (!userByCustomerEmail.isPresent())
                return ResponseEntity.badRequest().body(new UsernameNotFoundException("User doesnt exist").toString());
        GarageCustomers modifiedCustomer = userByCustomerEmail.get();
        modifiedCustomer.setFirstName(registrartionRequest.getFirstName());
        modifiedCustomer.setLastName(registrartionRequest.getLastName());
        modifiedCustomer.setCustomerEmail(registrartionRequest.getEmail());
        modifiedCustomer.setCustomerEmailPassword(registrartionRequest.getPassword());
        customerRepo.save(modifiedCustomer);
        return ResponseEntity.ok("User modified succesfully");


        }
}
