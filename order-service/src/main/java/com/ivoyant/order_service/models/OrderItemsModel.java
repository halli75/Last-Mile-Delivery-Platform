package com.ivoyant.order_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class OrderItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    public OrderItemsModel(String itemName, OrderModel order, int quantity) {
        this.itemName = itemName;
        this.order = order;
        this.quantity = quantity;
    }


}
