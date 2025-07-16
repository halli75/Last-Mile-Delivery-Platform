package com.ivoyant.driver_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "driverdatabase")
public class DriverModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int vehicleId;
    private boolean isActive;
    private boolean isAvailable;
    private double latitude;
    private double longitude;
    private double rating;
    private int deliveriesCompleted;
   

    public DriverModel() {
    }

    public DriverModel(int deliveriesCompleted, String email, String firstName, int id, boolean isActive, boolean isAvailable, String lastName, double latitude, double longitude, String phone, double rating, int vehicleId) {
        this.deliveriesCompleted = deliveriesCompleted;
        this.email = email;
        this.firstName = firstName;
        this.id = id;
        this.isActive = isActive;
        this.isAvailable = isAvailable;
        this.lastName = lastName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.rating = rating;
        this.vehicleId = vehicleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getDeliveriesCompleted() {
        return deliveriesCompleted;
    }

    public void setDeliveriesCompleted(int deliveriesCompleted) {
        this.deliveriesCompleted = deliveriesCompleted;
    }


}
