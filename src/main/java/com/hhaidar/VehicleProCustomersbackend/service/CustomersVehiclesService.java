package com.hhaidar.VehicleProCustomersbackend.service;

import com.hhaidar.VehicleProCustomersbackend.dto.VehicleRegisteryRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.CustomersVehicles;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface CustomersVehiclesService {
    ResponseEntity<String> addVehicle(Integer vehicleOwnerID, VehicleRegisteryRequestDTO vehicleRegisteryRequest);

    ResponseEntity<String> editVehicle(Integer vehicleOwnerID, Integer vehicleID, VehicleRegisteryRequestDTO vehicleRegisteryRequest);

    ResponseEntity<String> deleteVehicle(Integer vehicleOwnerID, Integer vehicleID);

    ResponseEntity<ArrayList<CustomersVehicles>> viewAllVehicles();

    ResponseEntity<ArrayList<CustomersVehicles>> viewVehicleOwnedBy(Integer vehicleOwnerID);
}
