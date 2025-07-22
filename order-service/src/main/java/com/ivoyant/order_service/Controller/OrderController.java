package com.ivoyant.order_service.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ivoyant.order_service.models.OrderModel;
import com.ivoyant.order_service.services.OrderService;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/orders")
    public int addOrder(@RequestBody OrderModel order){
        return orderService.addOrder(order);
    }

    @GetMapping("/orders/{orderId}")
    public OrderModel getOrder(@PathVariable int orderId){
        return orderService.getOrder(orderId);
    }

    @PutMapping("/orders/{orderId}/status")
    public void updateStatus(@PathVariable int orderId){
        orderService.setOrderStatus(orderId);
    }

    @GetMapping("/orders/customer/{customerId}")
    public List<OrderModel> getCustomerOrderHistory(@PathVariable int customerId){
        return orderService.getCustomerOrderHistory(customerId);
    }
}
