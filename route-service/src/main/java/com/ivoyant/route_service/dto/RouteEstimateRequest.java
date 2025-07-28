package com.ivoyant.route_service.dto;

import lombok.Value;

@Value
public class RouteEstimateRequest {
    String origin;
    String destination;
}

