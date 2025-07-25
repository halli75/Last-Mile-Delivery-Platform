package com.ivoyant.delivery_service.dto;

import lombok.Value;
import java.util.UUID;

@Value
public class CreateDeliveryRequest {
    UUID orderId;
    UUID driverId;
    UUID routeId;
}
