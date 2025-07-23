package com.ivoyant.vehicle_service.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoyant.vehicle_service.models.VehicleModel;
import com.ivoyant.vehicle_service.repositories.VehicleRepository;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public int addVehicle(VehicleModel vehicle){
        VehicleModel newVehicle = vehicleRepository.save(vehicle);
        return newVehicle.getId();
    }

    public VehicleModel getVehicle(int vehicleId){
        Optional<VehicleModel> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isPresent())
            return vehicle.get();
        return null;
    }

    public int setNextService(int vehicleId, LocalDate date){
        
        if(!vehicleRepository.existsById(vehicleId))
            return -1;
        
        vehicleRepository.setNextServiceDateById(vehicleId, date);
        return 1;
    }

    public List<VehicleModel> getVehicles(int driverId){
        return vehicleRepository.findByDriverId(driverId);
    }
}
