package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.*;
import org.springframework.http.ResponseEntity;



public interface GarageCustomerServices {
    ResponseEntity<String> registerCustomer(CustomerRegistrartionRequestDTO registrartionRequest);
    ResponseEntity<AuthenticationResponseDTO> authenticate(AuthRequestDTO authRequest);
    ResponseEntity<String> modifyAccountData(String email, CustomerDataModifyDTO dataModifyDTO);

    ResponseEntity<UserInfoResponseDTO> getUserInfo(String email);

    ResponseEntity<String> updatePassword(String email, CustomerPasswordChangeDTO passwordChangeDTO);
}
