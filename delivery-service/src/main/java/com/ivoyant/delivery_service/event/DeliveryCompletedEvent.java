package com.ivoyant.delivery_service.event;

import lombok.Value;
import java.time.Instant;
import java.util.UUID;

@Value
public class DeliveryCompletedEvent {
    UUID deliveryId;
    UUID orderId;
    UUID driverId;
    Instant completedAt;
    String proof;
}
