package com.ivoyant.order_service.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_status_history")
public class OrderStatusHistoryModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId; // FK to OrderModel (can be made a proper @ManyToOne)

    private String status; // e.g. "PLACED", "EN_ROUTE", "DELIVERED"

    private LocalDateTime timestamp; // when the status was recorded

    public OrderStatusHistoryModel(int orderId, String status) {
        this.orderId = orderId;
        this.status = status;

        timestamp = LocalDateTime.now();
    }



}
