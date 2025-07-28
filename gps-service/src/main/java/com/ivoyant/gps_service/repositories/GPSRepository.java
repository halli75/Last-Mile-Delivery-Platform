package com.ivoyant.gps_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

import com.ivoyant.gps_service.models.GPSModel;

public interface GPSRepository extends MongoRepository<GPSModel, String> {
    List<GPSModel> findByDriverId(String driverId);
}
