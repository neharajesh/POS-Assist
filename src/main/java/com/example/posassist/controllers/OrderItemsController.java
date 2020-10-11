package com.example.posassist.controllers;

import com.example.posassist.dto.request.OrderItemsDTO;
import com.example.posassist.services.interfaces.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(OrderItemsController.BASE_URL)
public class OrderItemsController {
    public static final String BASE_URL = "/api/v1/orderitems";

    @Autowired
    private OrderItemsService orderItemsService;

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderItemsService.findAllOrderItems(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemsService.findOrderItemsById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity<?> createOrderItem(@RequestBody OrderItemsDTO orderItemsDTO) {
        return new ResponseEntity<>(orderItemsService.addItemToOrder(orderItemsDTO), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public @ResponseBody ResponseEntity<?> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemsDTO orderItemsDTO) {
        return new ResponseEntity<>(orderItemsService.updateOrderItems(id, orderItemsDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody ResponseEntity<?> delete(@PathVariable Long id) {
        orderItemsService.deleteOrderItems(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}