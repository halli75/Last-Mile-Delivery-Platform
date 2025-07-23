package com.ivoyant.vehicle_service.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivoyant.vehicle_service.models.VehicleModel;
import com.ivoyant.vehicle_service.services.VehicleService;

@RestController
public class VehicleController {
    
    @Autowired
    VehicleService vehicleService;

    @PostMapping("/vehicles")
    public int registerDriver(@RequestBody VehicleModel vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/vehicles/{vehicleId}")
    public VehicleModel getVehicle(@PathVariable int vehicleId){
        return vehicleService.getVehicle(vehicleId);
    }

    @GetMapping("/vehicles/driver/{driverId}")
    public List<VehicleModel> getDriverVehicles(@PathVariable int driverId){
        return vehicleService.getVehicles(driverId);
    }
    
    @PutMapping("/vehicles/{vehicleId}/maintenance")
    public void setMaintenance(@PathVariable int vehicleId, @RequestBody LocalDate date) {
        vehicleService.setNextService(vehicleId, date);
    }

}
