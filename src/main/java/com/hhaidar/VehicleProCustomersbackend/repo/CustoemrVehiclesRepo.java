package com.hhaidar.VehicleProCustomersbackend.repo;

import com.hhaidar.VehicleProCustomersbackend.model.CustomersVehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CustoemrVehiclesRepo extends JpaRepository<CustomersVehicles,Integer> {
    Optional<CustomersVehicles> findByVehicleIdAndVehicleOwnerID(Integer vehicleID, Integer vehicleOwnerID);


    ArrayList<CustomersVehicles> findAllByVehicleOwnerID(Integer vehicleOwnerID);
}
