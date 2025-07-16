package com.ivoyant.driver_service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<DriverModel, Integer> {


    DriverModel save(DriverModel driver);

    List<DriverModel> findByIsAvailableTrue();

    //List<DriverModel> findAvailableDriversInRange(double latitude, double longitude, double range);

    //DriverModel findById(int id);
}
