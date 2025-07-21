package com.ivoyant.order_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivoyant.order_service.models.OrderModel;
import com.ivoyant.order_service.models.OrderStatusHistoryModel;
import com.ivoyant.order_service.repositories.OrderRepository;
import com.ivoyant.order_service.repositories.OrderStatusHistoryRepository;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository; 
    @Autowired
    OrderStatusHistoryRepository orderStatusHistoryRepository;


    public void setOrderStatus(int orderId){
        OrderModel order = getOrder(orderId);
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
                    break;
                }
            }
    }

    public OrderModel getOrder(int orderId){
        Optional<OrderModel> possibleOrder = orderRepository.findById(orderId);
        if(possibleOrder.isPresent()){
            return possibleOrder.get();
        }
        return null;

    }
}
