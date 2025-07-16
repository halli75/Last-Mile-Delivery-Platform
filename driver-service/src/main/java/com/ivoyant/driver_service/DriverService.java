package com.ivoyant.driver_service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public DriverModel registerDriver(DriverModel driver) {
        System.out.println("Saving driver");
        return driverRepository.save(driver);
    }

    public Optional<DriverModel> findById(int id) {
        return driverRepository.findById(id);
    }

    public double findRating(int id) {
        Optional<DriverModel> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            DriverModel driver = optionalDriver.get();
            return driver.getRating();
        }
        return -1;
    }

    public void setAvailable(int id, boolean isAvailable) {
        Optional<DriverModel> optionalDriver = driverRepository.findById(id);
        if (optionalDriver.isPresent()) {
            DriverModel driver = optionalDriver.get();
            driver.setIsAvailable(isAvailable);
            driverRepository.save(driver);
        }
    }

    public void deleteDriver(int id) {
        driverRepository.deleteById(id);
    }

    public List<DriverModel> findAvailableDrivers() {
        return driverRepository.findByIsAvailableTrue();
    }
}
    