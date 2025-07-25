package com.ivoyant.delivery_service.controller;

import com.ivoyant.delivery_service.dto.CreateDeliveryRequest;
import com.ivoyant.delivery_service.entity.Delivery;
import com.ivoyant.delivery_service.entity.Delivery.Status;
import com.ivoyant.delivery_service.event.DeliveryCompletedEvent;
import com.ivoyant.delivery_service.event.DeliveryStartedEvent;
import com.ivoyant.delivery_service.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {
    private final DeliveryRepository repo;
    private final ApplicationEventPublisher events;

    @PostMapping
    public ResponseEntity<Delivery> create(@RequestBody CreateDeliveryRequest req) {
        Delivery d = new Delivery();
        d.setOrderId(req.getOrderId());
        d.setDriverId(req.getDriverId());
        d.setRouteId(req.getRouteId());
        d.setStatus(Status.CREATED);
        d.setCreatedAt(Instant.now());
        Delivery saved = repo.save(d);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<Delivery> start(@PathVariable UUID id) {
        return repo.findById(id)
                .map(d -> {
                    d.setStatus(Status.STARTED);
                    d.setStartedAt(Instant.now());
                    Delivery updated = repo.save(d);
                    events.publishEvent(new DeliveryStartedEvent(
                            updated.getId(), updated.getOrderId(), updated.getDriverId(), updated.getStartedAt()));
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Delivery> complete(@PathVariable UUID id) {
        return repo.findById(id)
                .map(d -> {
                    d.setStatus(Status.COMPLETED);
                    d.setCompletedAt(Instant.now());
                    d.setProof("TEMP_PROOF");
                    Delivery updated = repo.save(d);
                    events.publishEvent(new DeliveryCompletedEvent(
                            updated.getId(), updated.getOrderId(), updated.getDriverId(), updated.getCompletedAt(), updated.getProof()));
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/proof")
    public ResponseEntity<Delivery> proof(@PathVariable UUID id,
                                          @RequestBody String proof) {
        return repo.findById(id)
                .map(d -> {
                    d.setProof(proof);
                    Delivery updated = repo.save(d);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
