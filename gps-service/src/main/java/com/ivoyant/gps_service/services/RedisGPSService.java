package com.ivoyant.gps_service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ivoyant.gps_service.models.GPSModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ivoyant.gps_service.models.GPSRedisModel;

@Service
public class RedisGPSService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final ObjectMapper mapper;

    public RedisGPSService() {
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
        this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    private final String PREFIX = "location:";

    public void saveCurrentLocation(GPSModel location) {
        try {
            GPSRedisModel dto = new GPSRedisModel();
            dto.setDriverId(location.getDriverId());
            dto.setLatitude(location.getLocation().getY());
            dto.setLongitude(location.getLocation().getX());
            dto.setTimestamp(location.getTimestamp().toString());

            String key = PREFIX + dto.getDriverId();
            String value = mapper.writeValueAsString(dto);
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GPSModel getCurrentLocation(String driverId) {
        try {
            String value = redisTemplate.opsForValue().get(PREFIX + driverId);
            if (value == null) return null;

            GPSRedisModel dto = mapper.readValue(value, GPSRedisModel.class);
            GPSModel gps = new GPSModel();
            gps.setDriverId(dto.getDriverId());
            gps.setLocation(new org.springframework.data.mongodb.core.geo.GeoJsonPoint(dto.getLongitude(), dto.getLatitude()));
            gps.setTimestamp(java.time.LocalDateTime.parse(dto.getTimestamp()));
            return gps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
