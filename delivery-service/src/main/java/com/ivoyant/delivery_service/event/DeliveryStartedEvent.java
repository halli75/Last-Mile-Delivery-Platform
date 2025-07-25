package com.ivoyant.delivery_service.event;

import lombok.Value;
import java.time.Instant;
import java.util.UUID;

@Value
public class DeliveryStartedEvent {
    UUID deliveryId;
    UUID orderId;
    UUID driverId;
    Instant startedAt;
}
