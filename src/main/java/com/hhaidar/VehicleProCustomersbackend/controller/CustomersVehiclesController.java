package com.hhaidar.VehicleProCustomersbackend.controller;

import com.hhaidar.VehicleProCustomersbackend.dto.VehicleRegisteryRequestDTO;
import com.hhaidar.VehicleProCustomersbackend.model.CustomersVehicles;
import com.hhaidar.VehicleProCustomersbackend.service.CustomersVehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
public class CustomersVehiclesController {
    private final CustomersVehiclesService vehiclesService;

    @PostMapping("/add/{vehicleOwnerID}")
    public ResponseEntity<String> addVehicle(@PathVariable Integer vehicleOwnerID,@RequestBody
    VehicleRegisteryRequestDTO vehicleRegisteryRequest ){
        return vehiclesService.addVehicle(vehicleOwnerID,vehicleRegisteryRequest);
    }

    @PutMapping ("/edit/{vehicleOwnerID}/{vehicleID}")
    public ResponseEntity<String> editVehicle(@PathVariable Integer vehicleOwnerID,@PathVariable Integer vehicleID,@RequestBody
    VehicleRegisteryRequestDTO vehicleRegisteryRequest ){
        return vehiclesService.editVehicle(vehicleOwnerID,vehicleID,vehicleRegisteryRequest);
    }
    @DeleteMapping  ("/delete/{vehicleOwnerID}/{vehicleID}")
    public ResponseEntity<String> deleteVehicle(@PathVariable Integer vehicleOwnerID,@PathVariable Integer vehicleID)
    {
        return vehiclesService.deleteVehicle(vehicleOwnerID,vehicleID);
    }

    @GetMapping("/view/all")
    public ResponseEntity<ArrayList<CustomersVehicles>> viewAllVehicles(){
        return vehiclesService.viewAllVehicles();
    }
    @GetMapping("/view/ownedby/{vehicleOwnerID}")
    public ResponseEntity<ArrayList<CustomersVehicles>> viewVehicleOwnedBy(@PathVariable Integer vehicleOwnerID){
        return vehiclesService.viewVehicleOwnedBy(vehicleOwnerID);
    }
}
