package com.ivoyant.route_service.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteStop {
    @Id @GeneratedValue private UUID id;
    private UUID routeId;
    private int stopOrder;
    private String location;  // "lat,lng"
    private Instant eta;
}
