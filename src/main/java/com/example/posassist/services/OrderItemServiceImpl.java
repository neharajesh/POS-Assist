package com.example.posassist.services;

import java.util.List;
import java.util.Optional;

import com.example.posassist.dto.request.OrderItemDTO;
import com.example.posassist.entities.Item;
import com.example.posassist.entities.OrderItem;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.OrderItemRepository;
import com.example.posassist.services.interfaces.ItemService;
import com.example.posassist.services.interfaces.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public OrderItem addItemToOrder(OrderItemDTO orderItemDTO) {

        Item itemInCart = itemService.findItemById(orderItemDTO.getItem());

        OrderItem orderItem = OrderItem.builder()
                .item(itemInCart)
                .quantity(orderItemDTO.getQuantity())
                .instructions(orderItemDTO.getInstructions())
                .build();

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem findOrderItemsById(Long id) {
        Optional<OrderItem> orderItems = orderItemRepository.findById(id);
        if(!orderItems.isPresent())
            throw new ResourceNotFoundException("Item not found in order");
        return orderItems.get();
    }

    @Override
    public OrderItem updateOrderItems(Long id, OrderItemDTO orderItemDTO) {
        OrderItem orderItem = findOrderItemsById(id);
        orderItem.setInstructions(orderItem.getInstructions());
        orderItem.setQuantity(orderItem.getQuantity());
        orderItem.setItem(orderItem.getItem());
        return orderItemRepository.save(orderItem);
    }

    @Override
    public void deleteOrderItems(Long id) {
        orderItemRepository.delete(findOrderItemsById(id));
    }

    @Override
    public List<OrderItem> findAllOrderItems() {
        return orderItemRepository.findAll();
    }

}