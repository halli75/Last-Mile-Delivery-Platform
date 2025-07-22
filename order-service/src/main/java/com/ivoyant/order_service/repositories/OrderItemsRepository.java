package com.ivoyant.order_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivoyant.order_service.models.OrderItemsModel;

public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Integer> {
    
    List<OrderItemsModel> findByOrder_OrderId(int orderId);
}
