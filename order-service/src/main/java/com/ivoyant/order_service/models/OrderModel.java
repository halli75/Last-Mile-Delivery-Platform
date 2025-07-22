package com.ivoyant.order_service.models;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "order_table")
public class OrderModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    private String restaurant;
    private double restaurantLong;
    private double restaurantLat;

    private double customerLong;
    private double customerLat;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<OrderItemsModel> itemsOrdered;

    private LocalDateTime timeOrdered = LocalDateTime.now();

    private String orderStatus = "PLACED";

    private int driverId;
    private int customerId;

    public OrderModel() {}

    public OrderModel(int customerId, double customerLat, double customerLong, List<OrderItemsModel> itemsOrdered, String restaurant, double restaurantLat, double restaurantLong, int driverId) {
        this.customerId = customerId;
        this.customerLat = customerLat;
        this.customerLong = customerLong;
        this.itemsOrdered = itemsOrdered;
        this.restaurant = restaurant;
        this.restaurantLat = restaurantLat;
        this.restaurantLong = restaurantLong;
        this.driverId = driverId;

        this.timeOrdered = LocalDateTime.now();
        this.orderStatus = "PLACED";

        if (this.itemsOrdered != null) {
            for (OrderItemsModel item : this.itemsOrdered) {
                item.setOrder(this);
            }
        }
    }

    
}
