package com.example.posassist.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import com.example.posassist.dto.request.OrderDTO;
import com.example.posassist.entities.Order;
import com.example.posassist.enums.OrderType;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.OrderRepository;
import com.example.posassist.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if(!order.isPresent())
            throw new ResourceNotFoundException("Order not found!");
        return order.get();
    }

    @Override
    public Order findOrderByDate(Date date) {
        Optional<Order> order = orderRepository.findByDateOfOrder(date);
        if(!order.isPresent())
            throw new ResourceNotFoundException("Order not found!");
        return order.get();
    }

    @Override
    @Transactional
    public Order saveOrder(OrderDTO orderDTO) {
        //add stuff to the order.
        //stuff from the cart that is.

        Order order = Order.builder()
                .orderName("OrderName")	//change value
                .orderDetails("OrderDetails")	//change value
                .dateOfOrder(new Date())
                .total(0.0)	//change value
                .orderType(OrderType.TRIAL)
                .build();
//
//		OrderType orderType = orderDTO.getOrderType();
//		orderType.forEach(type -> {
//			switch(type) {
//			case DINE_IN :
//
//			}
//		});

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Long id, OrderDTO orderDTO) {
        Order order = findOrderById(id);
        order.setOrderName(orderDTO.getOrderName());
        order.setOrderType(orderDTO.getOrderType());
        order.setTotal(orderDTO.getTotal());
        order.setOrderDetails(orderDTO.getOrderDetails());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.delete(findOrderById(id));
    }

}