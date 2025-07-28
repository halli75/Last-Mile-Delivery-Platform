package com.ivoyant.route_service.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id @GeneratedValue private UUID id;
    private UUID orderId;
    private double totalDistanceKm;
    private long estimatedDurationMin;
    private Instant createdAt = Instant.now();
}

