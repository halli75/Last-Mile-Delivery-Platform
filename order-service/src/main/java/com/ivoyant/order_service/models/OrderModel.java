package com.ivoyant.order_service.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ivoyant.order_service.repositories.OrderStatusHistoryRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "orderdatabase")
public class OrderModel implements Serializable {
    private int orderId;

    private String restaurant;
    private double restaurantLong;
    private double restaurantLat;

    private double customerLong;
    private double customerLat;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemsModel> itemsOrdered;

    private LocalDateTime timeOrdered;

    private String orderStatus;

    private int driverId;
    private int customerId;

    @Autowired
    private OrderStatusHistoryRepository newStatusRepo;

    public OrderModel(int customerId, double customerLat, double customerLong, List<OrderItemsModel> itemsOrdered, String restaurant, double restaurantLat, double restaurantLong) {
        this.customerId = customerId;
        this.customerLat = customerLat;
        this.customerLong = customerLong;
        this.itemsOrdered = itemsOrdered;
        this.restaurant = restaurant;
        this.restaurantLat = restaurantLat;
        this.restaurantLong = restaurantLong;

        timeOrdered = LocalDateTime.now();

        orderStatus = "PLACED";

        
    }

    
}
