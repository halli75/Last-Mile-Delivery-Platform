package com.ivoyant.gps_service.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import com.ivoyant.gps_service.models.GPSModel;
import com.ivoyant.gps_service.models.GeoFenceInputModel;
import com.ivoyant.gps_service.models.GeoFenceModel;
import com.ivoyant.gps_service.repositories.GPSRepository;

@Service
public class GPSService {
    @Autowired
    GPSRepository gPSRepository;
    @Autowired
    RedisGPSService redisGPSService;

    public String addGPS(GPSModel location){
        System.out.println("🔍 Received in controller: " + location);
        location.setTimestamp(LocalDateTime.now());
        GPSModel savedLocation = gPSRepository.save(location);
        System.out.println("SAVED TO MONGO: " + savedLocation);
        redisGPSService.saveCurrentLocation(savedLocation);
        return savedLocation.getId();
    }

    public GPSModel getLocation(String driverId){
        return redisGPSService.getCurrentLocation(driverId);
    }

    public GeoFenceModel createGeoFence(GeoFenceInputModel input){
        GeoFenceModel fence = new GeoFenceModel();

        GeoJsonPoint customer = input.getCustomer();
        GeoJsonPoint restaurant = input.getRestaurant();

        double minLon = Math.min(customer.getX(), restaurant.getX());
        double maxLon = Math.max(customer.getX(), restaurant.getX());
        double minLat = Math.min(customer.getY(), restaurant.getY());
        double maxLat = Math.max(customer.getY(), restaurant.getY());

        double padding = .005;
        maxLon += padding;
        maxLat += padding;
        minLon -= padding;
        minLat -= padding;
        

        List<GeoJsonPoint> fencePoints = new ArrayList<>();

        fencePoints.add(new GeoJsonPoint(minLon, minLat)); // bottom-left
        fencePoints.add(new GeoJsonPoint(minLon, maxLat)); // top-left
        fencePoints.add(new GeoJsonPoint(maxLon, maxLat)); // top-right
        fencePoints.add(new GeoJsonPoint(maxLon, minLat)); // bottom-right
        fencePoints.add(new GeoJsonPoint(minLon, minLat)); // close loop

        fence.setBoundaryPoints(fencePoints);

        return fence;
    }


}
