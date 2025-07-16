package com.ivoyant.driver_service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/drivers")
    public void registerDriver(@RequestBody DriverModel driver){
        driverService.registerDriver(driver);
    }

    @PutMapping("/drivers/{id}/availability")
    public void updateDriverAvailability(@PathVariable int id, @RequestBody boolean isAvailable){
        driverService.setAvailable(id, isAvailable);
    }

    @GetMapping("/drivers/{id}/performance")
    public double getDriverPerformance(@PathVariable int id){
        return driverService.findRating(id);
    }
    
    @DeleteMapping("/drivers/{id}/remove")
    public void deleteDriver(@PathVariable int id) {
        driverService.deleteDriver(id);
    }

    @GetMapping("/drivers/available")
    public List<DriverModel> getAvailableDrivers() {
        return driverService.findAvailableDrivers();
    }
    
}
