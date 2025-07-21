package com.ivoyant.order_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoyant.order_service.models.OrderStatusHistoryModel;

public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistoryModel, Integer> {
}
