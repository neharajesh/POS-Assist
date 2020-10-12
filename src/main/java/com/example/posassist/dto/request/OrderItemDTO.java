package com.example.posassist.dto.request;

import com.example.posassist.entities.Item;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Item item;

    private String instructions;

    private Integer quantity;
}