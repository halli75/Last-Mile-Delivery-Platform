package com.ivoyant.delivery_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deliveries")
@Data
@NoArgsConstructor
@Getter @Setter
@AllArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID orderId;
    private UUID driverId;
    private UUID routeId;

    @Enumerated(EnumType.STRING)
    private Status status = Status.CREATED;

    private Instant createdAt = Instant.now();
    private Instant startedAt;
    private Instant completedAt;
    private String proof;

    public enum Status {
        CREATED,
        STARTED,
        COMPLETED,
        FAILED
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getOrderId() {
        return orderId;
    }
    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }
    public UUID getDriverId() {
        return driverId;
    }
    public void setDriverId(UUID driverId) {
        this.driverId = driverId;
    }
    public UUID getRouteId() {
        return routeId;
    }
    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

}
