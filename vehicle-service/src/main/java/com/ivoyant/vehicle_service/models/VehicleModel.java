package com.ivoyant.vehicle_service.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "vehicle_table")
public class VehicleModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; 
    private int driverId;
    
    private String licensePlate;
    private String make;
    private String model;
    private int year;
    private String vin;
    private String type;         // e.g., van, truck
    
    private LocalDate lastServiceDate;
    private int mileage;
    private String lastServiceDescription;
    private double lastServiceCost;
    private String lastServicedBy;
    private LocalDate nextServiceDue;
    private LocalDate nextServiceDate; 

}
