package com.ivoyant.gps_service.models;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;




public class GeoFenceInputModel {
    private GeoJsonPoint restaurant;
    private GeoJsonPoint customer;

    public GeoJsonPoint getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(GeoJsonPoint restaurant) {
        this.restaurant = restaurant;
    }

    public GeoJsonPoint getCustomer() {
        return customer;
    }

    public void setCustomer(GeoJsonPoint customer) {
        this.customer = customer;
    }


}
