package com.example.posassist.services;

import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.posassist.entities.*;
import com.example.posassist.security.services.UserPrinciple;
import com.example.posassist.dto.request.OrderDTO;
import com.example.posassist.enums.OrderStatus;
import com.example.posassist.enums.OrderType;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.OrderItemRepository;
import com.example.posassist.repositories.OrderRepository;
import com.example.posassist.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private InventoryService inventoryService;


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

        List<Double> costList = new ArrayList<>();
        Set<OrderItem> orderItemsList = new HashSet<>();


        orderDTO.getOrderItems().forEach(orderItemDTO -> {
            Item items = itemService.findItemById(orderItemDTO.getItemId());
            costList.add(items.getPrice() * orderItemDTO.getQuantity());
            orderItemsList.add(orderItemService.addItemToOrder(orderItemDTO));
        });

        Set<OrderItem> orderItemsSet = orderItemsList.stream().map(
                orderItem -> orderItemRepository.save(orderItem)
        ).collect(Collectors.toSet());

        Double cost = costList.stream().reduce((double) 0, Double::sum);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple user = (UserPrinciple) auth.getPrincipal();

        Order order = Order.builder()
                .customerName(user.getName())
                .orderItems(orderItemsSet)
                .dateOfOrder(new Date())
                .total(cost)
                .orderStatus(OrderStatus.PLACED)
                .orderType(OrderType.valueOf(orderDTO.getOrderType()))
                .build();

        inventoryService.updateInventory(order);

        return orderRepository.save(order);
    }


    @Override
    public void deleteOrder(Long id) {
        orderRepository.delete(findOrderById(id));
    }

    @Override
    public Map<Long, Double> orderIngredientQuantities(Order order) {
        Map<Long, Double> fromRecipe = new HashMap<>();
        Set<OrderItem> orderItemSet = order.getOrderItems();

        for (OrderItem orderItem: orderItemSet) {
            Long itemId = orderItem.getItem().getId();
            Double quantityOrdered = orderItem.getQuantity();
            Set<Ingredient> ingredientList = recipeService.findByItem(itemId).getIngredientQuantities();
            for(Ingredient ingredient : ingredientList) {
                if(fromRecipe.containsKey(ingredient.getInventory().getId())){
                    fromRecipe.put(ingredient.getInventory().getId(), fromRecipe.get(ingredient.getInventory().getId()) + ingredient.getQuantity() * quantityOrdered);
                }
                else
                    fromRecipe.put(ingredient.getInventory().getId(), ingredient.getQuantity() * quantityOrdered);
            }
        }
        return fromRecipe;
    }


}