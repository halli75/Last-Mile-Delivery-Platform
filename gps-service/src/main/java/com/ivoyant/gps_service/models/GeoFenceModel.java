package com.ivoyant.gps_service.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@NoArgsConstructor
@Document(collection = "delivery_zones")
public class GeoFenceModel {
    @Id
    private String id;
    private List<GeoJsonPoint> boundaryPoints; // Polygon vertices

    
    // getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GeoJsonPoint> getBoundaryPoints() {
        return boundaryPoints;
    }

    public void setBoundaryPoints(List<GeoJsonPoint> boundaryPoints) {
        this.boundaryPoints = boundaryPoints;
    }
}
