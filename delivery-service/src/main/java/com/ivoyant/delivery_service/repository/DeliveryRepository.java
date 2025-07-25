package com.ivoyant.delivery_service.repository;

import com.ivoyant.delivery_service.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {
    Delivery findByOrderId(int orderId);
    Delivery findByDriverId(int driverId);
    Delivery findByVehicleId(int vehicleId);
    Delivery findByCustomerId(int customerId);
    Delivery findByStatus(String status);
    Delivery findByDriverIdAndStatus(int driverId, String status);
    Delivery findByVehicleIdAndStatus(int vehicleId, String status);
    Delivery findByCustomerIdAndStatus(int customerId, String status);
    Delivery findByOrderIdAndStatus(int orderId, String status);
    Delivery findByRouteId(int routeId);
    Delivery findByRouteIdAndStatus(int routeId, String status);
    Delivery findByRouteIdAndDriverId(int routeId, int driverId);
    Delivery findByRouteIdAndVehicleId(int routeId, int vehicleId);
    Delivery findByRouteIdAndCustomerId(int routeId, int customerId);
    Delivery findByRouteIdAndOrderId(int routeId, int orderId);
    Delivery findByRouteIdAndStatusAndDriverId(int routeId, String status, int driverId);
    Delivery findByRouteIdAndStatusAndVehicleId(int routeId, String status, int vehicleId);
    Delivery findByRouteIdAndStatusAndCustomerId(int routeId, String status, int customerId);
    Delivery findByRouteIdAndStatusAndOrderId(int routeId, String status, int orderId);
    Delivery findByRouteIdAndStatusAndDriverIdAndVehicleId(int routeId, String status, int driverId, int vehicleId);
    Delivery findByRouteIdAndStatusAndDriverIdAndCustomerId(int routeId, String status, int driverId, int customerId);
    Delivery findByRouteIdAndStatusAndDriverIdAndOrderId(int routeId, String status, int driverId, int orderId);
    Delivery findByRouteIdAndStatusAndVehicleIdAndCustomerId(int routeId, String status, int vehicleId, int customerId);
    Delivery findByRouteIdAndStatusAndVehicleIdAndOrderId(int routeId, String status, int vehicleId, int orderId);
    Delivery findByRouteIdAndStatusAndCustomerIdAndOrderId(int routeId, String status, int customerId, int orderId);
    Delivery findByRouteIdAndStatusAndDriverIdAndVehicleIdAndCustomerId(int routeId, String status, int driverId, int vehicleId, int customerId);
    Delivery findByRouteIdAndStatusAndDriverIdAndVehicleIdAndOrderId(int routeId, String status, int driverId, int vehicleId, int orderId);
    Delivery findByRouteIdAndStatusAndDriverIdAndCustomerIdAndOrderId(int routeId, String status, int driverId, int customerId, int orderId);
    Delivery findByRouteIdAndStatusAndVehicleIdAndCustomerIdAndOrderId(int routeId, String status, int vehicleId, int customerId, int orderId);
    Delivery findByRouteIdAndStatusAndCustomerIdAndVehicleIdAndOrderId(int routeId, String status, int customerId, int vehicleId, int orderId);
    Delivery findByRouteIdAndStatusAndDriverIdAndCustomerIdAndVehicleIdAndOrderId(int routeId, String status, int driverId, int customerId, int vehicleId, int orderId);
    Delivery findByRouteIdAndStatusAndDriverIdAndVehicleIdAndCustomerIdAndOrderId(int routeId, String status, int driverId, int vehicleId, int customerId, int orderId);

}
