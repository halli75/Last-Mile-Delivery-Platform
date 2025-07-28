package com.ivoyant.route_service.controller;

import com.ivoyant.route_service.entities.Route;
import com.ivoyant.route_service.entities.RouteStop;
import com.ivoyant.route_service.dto.OptimizeRouteRequest;
import com.ivoyant.route_service.dto.RouteEstimateRequest;
import com.ivoyant.route_service.repository.RouteRepository;
import com.ivoyant.route_service.repository.RouteStopRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.*;
import java.util.UUID;


@RestController
@RequestMapping("/routes")
@RequiredArgsConstructor

public class RouteController {

    private final RouteRepository routeRepo;
    private final RouteStopRepository stopRepo;

    @PostMapping("/optimize")
    public ResponseEntity<Route> optimize(@RequestBody OptimizeRouteRequest req) {
        double fakeDistance = 12.5;  // km
        long estTime = (long) ((fakeDistance / 40.0) * 60);  // minutes

        Route r = routeRepo.save(new Route(null, req.getOrderId(), fakeDistance, estTime, Instant.now()));

        int i = 0;
        stopRepo.save(new RouteStop(null, r.getId(), i++, req.getOrigin(), Instant.now().plusSeconds(60)));
        for (String s : req.getStops()) {
            stopRepo.save(new RouteStop(null, r.getId(), i++, s, Instant.now().plusSeconds(300)));
        }
        stopRepo.save(new RouteStop(null, r.getId(), i, req.getDestination(), Instant.now().plusSeconds(600)));

        return ResponseEntity.ok(r);
    }

    @GetMapping("/{routeId}")
    public ResponseEntity<Map<String, Object>> get(@PathVariable UUID routeId) {
        Optional<Route> route = routeRepo.findById(routeId);
        if (route.isEmpty()) return ResponseEntity.notFound().build();

        List<RouteStop> stops = stopRepo.findByRouteIdOrderByStopOrder(routeId);

        Map<String, Object> res = new HashMap<>();
        res.put("route", route.get());
        res.put("stops", stops);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{routeId}/update")
    public ResponseEntity<Route> updateRoute(@PathVariable UUID routeId,
                                             @RequestBody List<String> newStops) {
        Optional<Route> rOpt = routeRepo.findById(routeId);
        if (rOpt.isEmpty()) return ResponseEntity.notFound().build();

        stopRepo.deleteAll(stopRepo.findByRouteIdOrderByStopOrder(routeId));

        int i = 0;
        for (String stop : newStops) {
            stopRepo.save(new RouteStop(null, routeId, i++, stop, Instant.now().plusSeconds(i * 120)));
        }

        return ResponseEntity.ok(rOpt.get());
    }

    @GetMapping("/estimate")
    public ResponseEntity<Map<String, Object>> estimate(@RequestBody RouteEstimateRequest req) {
        double fakeDistance = 7.3;
        long estTime = (long) ((fakeDistance / 40.0) * 60);

        Map<String, Object> res = new HashMap<>();
        res.put("distance_km", fakeDistance);
        res.put("estimated_time_min", estTime);
        return ResponseEntity.ok(res);
    }
}

