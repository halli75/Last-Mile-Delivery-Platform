package com.ivoyant.route_service.repository;

import com.ivoyant.route_service.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {
}
