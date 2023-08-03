package com.hhaidar.VehicleProCustomersbackend.impl;

import com.hhaidar.VehicleProCustomersbackend.dto.VehicleRegisteryRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.CustomersVehicles;
import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import com.hhaidar.VehicleProCustomersbackend.repo.CustoemrVehiclesRepo;
import com.hhaidar.VehicleProCustomersbackend.repo.GarageCustomersRepo;
import com.hhaidar.VehicleProCustomersbackend.service.CustomersVehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerVehiclesServiceimpl  implements CustomersVehiclesService {
    private final GarageCustomersRepo vehicleOwnerRepo;
    private final CustoemrVehiclesRepo vehiclesRepo;
    @Override
    public ResponseEntity<String> addVehicle(Integer vehicleOwnerID, VehicleRegisteryRequestDTO vehicleRegisteryRequest) {
        Optional<GarageCustomers> vehicleOwner = vehicleOwnerRepo.findById(vehicleOwnerID);
        if (!vehicleOwner.isPresent())
            return ResponseEntity.badRequest().body(new UsernameNotFoundException("No owner with this id exist").toString());

         vehicleOwner = Optional.of(vehicleOwner.get());
        CustomersVehicles newVehicle = CustomersVehicles.builder()
                        .vehicleOwnerID(vehicleOwnerID)
                                .vehicleBrand(vehicleRegisteryRequest.getBrand())
                                        .vehicleModel(vehicleRegisteryRequest.getModel())
                                                .yearOfProduction(vehicleRegisteryRequest.getYearOfProduction())
                .build() ;
        vehiclesRepo.save(newVehicle);
        return ResponseEntity.ok(newVehicle +" ADDED SUCCESFULLY");
    }

    @Override
    public ResponseEntity<String> editVehicle(Integer vehicleOwnerID, Integer vehicleID, VehicleRegisteryRequestDTO vehicleRegisteryRequest) {
        Optional<CustomersVehicles> existingVehicle = vehiclesRepo.findByVehicleIdAndVehicleOwnerID(vehicleID,vehicleOwnerID);
        if (!existingVehicle.isPresent())
            return ResponseEntity.badRequest().body("No vehicle exist for this customerID ");
        CustomersVehicles modifiedVehicle = existingVehicle.get();
        modifiedVehicle.setVehicleBrand(vehicleRegisteryRequest.getBrand());
        modifiedVehicle.setVehicleModel(vehicleRegisteryRequest.getModel());
        modifiedVehicle.setYearOfProduction(vehicleRegisteryRequest.getYearOfProduction());
        vehiclesRepo.save(modifiedVehicle);
        return ResponseEntity.ok(existingVehicle.get() +" was edited to "+ modifiedVehicle);
    }

    @Override
    public ResponseEntity<String> deleteVehicle(Integer vehicleOwnerID, Integer vehicleID) {
        Optional<CustomersVehicles> existingVehicle = vehiclesRepo.findByVehicleIdAndVehicleOwnerID(vehicleID,vehicleOwnerID);
        if (!existingVehicle.isPresent())
            return ResponseEntity.badRequest().body("No vehicle exist for this customerID ");
        vehiclesRepo.delete(existingVehicle.get());
        return ResponseEntity.ok("Vehicle was deleted");
    }

    @Override
    public ResponseEntity<ArrayList<CustomersVehicles>> viewAllVehicles() {
        return ResponseEntity.ok((ArrayList<CustomersVehicles>)vehiclesRepo.findAll());
    }

    @Override
    public ResponseEntity<ArrayList<CustomersVehicles>> viewVehicleOwnedBy(Integer vehicleOwnerID) {
        return ResponseEntity.ok((ArrayList<CustomersVehicles>)vehiclesRepo.findAllByVehicleOwnerID(vehicleOwnerID));
    }
}
