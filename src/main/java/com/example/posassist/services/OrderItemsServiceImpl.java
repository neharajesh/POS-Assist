package com.example.posassist.services;

import java.util.List;
import java.util.Optional;

import com.example.posassist.dto.request.OrderItemsDTO;
import com.example.posassist.entities.OrderItems;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.OrderItemsRepository;
import com.example.posassist.services.interfaces.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public OrderItems addItemToOrder(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = OrderItems.builder()
                .quantity(orderItemsDTO.getQuantity())
                .instructions(orderItemsDTO.getInstructions())
                .item(orderItemsDTO.getItems())
                .build();

        return orderItemsRepository.save(orderItems);
    }

    @Override
    public OrderItems findOrderItemsById(Long id) {
        Optional<OrderItems> orderItems = orderItemsRepository.findById(id);
        if(!orderItems.isPresent())
            throw new ResourceNotFoundException("Item not found in order");
        return orderItems.get();
    }

    @Override
    public OrderItems updateOrderItems(Long id, OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = findOrderItemsById(id);
        orderItems.setInstructions(orderItems.getInstructions());
        orderItems.setQuantity(orderItems.getQuantity());
        orderItems.setItem(orderItems.getItem());
        return orderItemsRepository.save(orderItems);
    }

    @Override
    public void deleteOrderItems(Long id) {
        orderItemsRepository.delete(findOrderItemsById(id));
    }

    @Override
    public List<OrderItems> findAllOrderItems() {
        return orderItemsRepository.findAll();
    }

}