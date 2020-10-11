package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.OrderItemsDTO;
import com.example.posassist.entities.OrderItems;

import java.util.List;

public interface OrderItemsService {
    List<OrderItems> findAllOrderItems();

    OrderItems addItemToOrder(OrderItemsDTO orderItemsDTO);

    OrderItems findOrderItemsById(Long id);

    OrderItems updateOrderItems(Long id, OrderItemsDTO orderItemsDTO);

    void deleteOrderItems(Long id);
}
