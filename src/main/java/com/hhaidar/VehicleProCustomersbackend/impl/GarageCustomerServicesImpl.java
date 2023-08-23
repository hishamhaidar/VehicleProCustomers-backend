package com.hhaidar.VehicleProCustomersbackend.impl;

import com.hhaidar.VehicleProCustomersbackend.config.JWTService;
import com.hhaidar.VehicleProCustomersbackend.dto.*;
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
        Optional<GarageCustomers> customerExist = customerRepo.findByCustomerEmail(registrartionRequest.getEmail());
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
        Optional<GarageCustomers> customer = customerRepo.findByCustomerEmail(authRequest.getEmail());
        String jwtToken = jwtService.generateToken(customer.get());
        return ResponseEntity.ok(AuthenticationResponseDTO.builder()
                .jwtToken(jwtToken)
                .build());
    }

    @Override
    public ResponseEntity<String> modifyAccountData(String email, CustomerDataModifyDTO dataModifyDTO){
        Optional<GarageCustomers> userByCustomerEmail = customerRepo.findByCustomerEmail(email);
            if (!userByCustomerEmail.isPresent())
                return ResponseEntity.badRequest().body(new UsernameNotFoundException("User doesnt exist").toString());
        GarageCustomers modifiedCustomer = userByCustomerEmail.get();
        if(modifiedCustomer.getCustomerEmail().equals(dataModifyDTO.getEmail())) {
            modifiedCustomer.setFirstName(dataModifyDTO.getFirstName());
            modifiedCustomer.setLastName(dataModifyDTO.getLastName());
        }else {
            Optional<GarageCustomers> newUserEmail = customerRepo.findByCustomerEmail(dataModifyDTO.getEmail());
            if (newUserEmail.isPresent())
                return ResponseEntity.badRequest().body("New email is already taken");
            modifiedCustomer.setFirstName(dataModifyDTO.getFirstName());
            modifiedCustomer.setLastName(dataModifyDTO.getLastName());
            modifiedCustomer.setCustomerEmail(dataModifyDTO.getEmail());
        }

        customerRepo.save(modifiedCustomer);
        return ResponseEntity.ok("User modified succesfully");


        }

    @Override
    public ResponseEntity<UserInfoResponseDTO> getUserInfo(String email) {
        Optional<GarageCustomers> userByCustomerEmail = customerRepo.findByCustomerEmail(email);
        if (!userByCustomerEmail.isPresent())
            return ResponseEntity.badRequest().body(new UserInfoResponseDTO());
        UserInfoResponseDTO infoResponseDTO = UserInfoResponseDTO.builder()
                .userID(userByCustomerEmail.get().getCustomerID())
                .firstName(userByCustomerEmail.get().getFirstName())
                .lastName(userByCustomerEmail.get().getLastName())
                .build();
        return ResponseEntity.ok(infoResponseDTO);
    }

    @Override
    public ResponseEntity<String> updatePassword(String email, CustomerPasswordChangeDTO passwordChangeDTO) {
        Optional<GarageCustomers> customer = customerRepo.findByCustomerEmail(email);
        if (!customer.isPresent())
            return ResponseEntity.badRequest().body("User doesnt exist");
        if(!passwordEncoder.matches(passwordChangeDTO.getCurrentPassword(), customer.get().getCustomerEmailPassword()))

            return ResponseEntity.badRequest().body("Current password is incorrect");
        GarageCustomers modifiedCus = customer.get();
        modifiedCus.setCustomerEmailPassword(passwordEncoder.encode(passwordChangeDTO.getNewPassword()));
        customerRepo.save(modifiedCus);
        return ResponseEntity.ok("Password changed sucessfully");
    }
}
