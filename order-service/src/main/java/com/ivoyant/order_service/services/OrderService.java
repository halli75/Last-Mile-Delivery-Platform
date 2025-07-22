package com.ivoyant.order_service.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoyant.order_service.models.OrderItemsModel;
import com.ivoyant.order_service.models.OrderModel;
import com.ivoyant.order_service.models.OrderStatusHistoryModel;
import com.ivoyant.order_service.repositories.OrderItemsRepository;
import com.ivoyant.order_service.repositories.OrderRepository;
import com.ivoyant.order_service.repositories.OrderStatusHistoryRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository; 
    @Autowired
    OrderStatusHistoryRepository orderStatusHistoryRepository;
    @Autowired
    OrderItemsRepository orderItemsRepository;


    public void setOrderStatus(int orderId){
        
        OrderModel order = getOrder(orderId);
        if(order == null){
            System.out.println("Order ID: " + orderId + " is not a legitimate order ID");
            return;
        }
        String currentStatus = order.getOrderStatus();
        
        String[] possibleStatus = {"PLACED", "FINDING_DRIVER", "DRIVER_ASSIGNED", 
                                "DRIVER_TO_RESTAURANT", "DRIVER_AT_RESTAURANT", 
                                "DRIVER_TO_CUSTOMER", "FINISHED_DELIVERY"};
        
        if(!currentStatus.equals("FINISHED_DELIVERY"))
            for (int i = 0; i < possibleStatus.length; i++) {
                if(currentStatus.equals(possibleStatus[i])){
                    currentStatus = possibleStatus[i+1];

                    order.setOrderStatus(currentStatus);
                    orderRepository.updateOrderStatusByOrderId(orderId, currentStatus);
            
                    OrderStatusHistoryModel newStatus = new OrderStatusHistoryModel(orderId, currentStatus);
                    orderStatusHistoryRepository.save(newStatus);
                    return;
                }
            }
        System.out.println("Status: " + currentStatus + " is not a legitimate status");    
    }
    public int addOrder(OrderModel order){
        OrderModel savedOrder = orderRepository.save(order);
        
        List<OrderItemsModel> items = order.getItemsOrdered();
        for (OrderItemsModel item : items) {
            item.setOrder(savedOrder);
            orderItemsRepository.save(item);
        }
        
        OrderStatusHistoryModel newStatus = new OrderStatusHistoryModel(savedOrder.getOrderId(), savedOrder.getOrderStatus());
        orderStatusHistoryRepository.save(newStatus);

        return savedOrder.getOrderId();
    }

    public OrderModel getOrder(int orderId){
        Optional<OrderModel> possibleOrder = orderRepository.findById(orderId);
        if(possibleOrder.isPresent()){
            return possibleOrder.get();
        }
        return null;

    }

    public List<OrderModel> getCustomerOrderHistory(int customerId){
        List<Integer> orderIds = orderRepository.findOrderIdsByCustomerId(customerId);

        List<OrderModel> rtn = new ArrayList<OrderModel>();
        for (Integer OrderId : orderIds) {
            rtn.add(getOrder(OrderId));
        }

        return rtn;
    }
}
