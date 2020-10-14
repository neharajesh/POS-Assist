package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.OrderItemDTO;
import com.example.posassist.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    List<OrderItem> findAllOrderItems();

    OrderItem addItemToOrder(OrderItemDTO orderItemDTO);

    OrderItem findOrderItemsById(Long id);

    void deleteOrderItems(Long id);
}
