package com.hhaidar.VehicleProCustomersbackend.repo;

import com.hhaidar.VehicleProCustomersbackend.model.GarageCustomers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GarageCustomersRepo extends JpaRepository<GarageCustomers,Integer> {
    public Optional<GarageCustomers> findByCustomerEmail(String customerEmail) ;
}
