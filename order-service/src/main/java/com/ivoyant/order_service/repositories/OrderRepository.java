package com.ivoyant.order_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ivoyant.order_service.models.OrderModel;

public interface OrderRepository extends JpaRepository<OrderModel, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE OrderModel o SET o.orderStatus = :status WHERE o.orderId = :orderId")
    void updateOrderStatusByOrderId(@Param("orderId") int orderId, @Param("status") String status);

    @Query("SELECT o.orderId FROM OrderModel o WHERE o.customerId = :customerId")
    List<Integer> findOrderIdsByCustomerId(@Param("customerId") int customerId);
}
