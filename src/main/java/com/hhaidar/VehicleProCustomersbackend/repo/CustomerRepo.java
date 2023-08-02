package com.hhaidar.VehicleProCustomersbackend.repo;

import com.hhaidar.VehicleProCustomersbackend.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepo extends JpaRepository<Customers,Integer> {
    public Optional<Customers> findUserByCustomerEmail(String customerEmail) ;
}
