package com.example.posassist.services.interfaces;
import com.example.posassist.dto.request.OrderDTO;
import com.example.posassist.entities.Order;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> findAllOrders();

    Order findOrderById(Long id);

    Order findOrderByDate(Date date);

    Order saveOrder(OrderDTO orderDTO);

    void deleteOrder(Long id);

    Map<Long, Double> orderIngredientQuantities(Order order);
}
