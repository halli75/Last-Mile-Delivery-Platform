package com.ivoyant.route_service.dto;

import lombok.Value;
import java.util.List;
import java.util.UUID;

@Value
public class OptimizeRouteRequest {
    UUID orderId;
    String origin;        // "lat,lng"
    String destination;   // "lat,lng"
    List<String> stops;   // optional
}
