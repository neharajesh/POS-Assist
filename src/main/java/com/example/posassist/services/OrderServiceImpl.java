package com.example.posassist.services;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.posassist.dto.request.OrderDTO;
import com.example.posassist.entities.Item;
import com.example.posassist.entities.Order;
import com.example.posassist.entities.OrderItem;
import com.example.posassist.entities.User;
import com.example.posassist.enums.OrderStatus;
import com.example.posassist.enums.OrderType;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.OrderItemRepository;
import com.example.posassist.repositories.OrderRepository;
import com.example.posassist.services.interfaces.ItemService;
import com.example.posassist.services.interfaces.OrderItemService;
import com.example.posassist.services.interfaces.OrderService;
import com.example.posassist.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemRepository orderItemRepository;


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
    public Order saveOrder(OrderDTO orderDTO, String customerName) {
        User orderedBy = userService.findUserByName(customerName);
        List<Double> costList = new ArrayList<>();
        Set<OrderItem> orderItemsList = new HashSet<>();

        orderDTO.getOrderItems().forEach(orderItemDTO -> {
            Item items = orderItemDTO.getItem();
            costList.add(Double.valueOf(items.getPrice()) * orderItemDTO.getQuantity());
            orderItemsList.add(OrderItem.builder()
                    .instructions(orderItemDTO.getInstructions())
                    .item(items)
                    .quantity(orderItemDTO.getQuantity())
                    .build());
        });

        Set<OrderItem> orderItemsSet = orderItemsList.stream().map(
                orderItem -> orderItemRepository.save(orderItem)
        ).collect(Collectors.toSet());
        Double cost = costList.stream().reduce(Double.valueOf(0), (e1, e2) -> e1 + e2);

        OrderType orderType = orderDTO.getOrderType();

        Order order = Order.builder()
                .orderName(orderedBy.getName())
                .orderDetails("OrderDetails")
                .orderItems(orderItemsSet)
                .dateOfOrder(new Date())
                .total(cost)	//change value
                .orderType(orderType)
                .orderStatus(OrderStatus.PLACED)
                .build();

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