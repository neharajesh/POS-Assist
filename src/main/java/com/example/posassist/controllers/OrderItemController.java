package com.example.posassist.controllers;

import com.example.posassist.dto.request.OrderItemDTO;
import com.example.posassist.services.interfaces.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(OrderItemController.BASE_URL)
public class OrderItemController {
    public static final String BASE_URL = "/api/v1/orderitems";

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderItemService.findAllOrderItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.findOrderItemsById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<?> createOrderItem(@RequestBody OrderItemDTO orderItemDTO) {
        return new ResponseEntity<>(orderItemService.addItemToOrder(orderItemDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Long id) {
        orderItemService.deleteOrderItems(id);
        return new ResponseEntity<>("The order item has been deleted successfully!", HttpStatus.OK);
    }
}