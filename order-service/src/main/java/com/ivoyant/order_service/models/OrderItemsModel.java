package com.ivoyant.order_service.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "order_items_table")
public class OrderItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String itemName;
    private int quantity;
    //private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private OrderModel order;

    public OrderItemsModel(String itemName, OrderModel order, int quantity ) {
        this.itemName = itemName;
        this.order = order;
        this.quantity = quantity;
        //this.price = price;
    }

    public OrderItemsModel(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
        //this.price = price;
    }

    public OrderItemsModel() {}


}
