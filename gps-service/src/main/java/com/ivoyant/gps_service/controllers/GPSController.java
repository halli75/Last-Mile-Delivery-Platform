package com.ivoyant.gps_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivoyant.gps_service.models.GPSModel;
import com.ivoyant.gps_service.models.GeoFenceInputModel;
import com.ivoyant.gps_service.models.GeoFenceModel;
import com.ivoyant.gps_service.services.GPSService;

@RestController
public class GPSController {
    @Autowired
    private GPSService gPSService;

    @PostMapping("/gps/location")
    public String saveLocation(@RequestBody GPSModel location){
        return gPSService.addGPS(location);
    }

    @GetMapping("/gps/driver/{driverId}")
    public ResponseEntity<?> getLocation(@PathVariable String driverId){
        GPSModel location = gPSService.getLocation(driverId);
        if (location == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No real-time location found for user " + driverId);
        return ResponseEntity.ok(location);
    }

    @PostMapping("/gps/geofence")
    public GeoFenceModel createGeoFence(@RequestBody GeoFenceInputModel input){
        return gPSService.createGeoFence(input);
    }
}
