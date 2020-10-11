package com.example.posassist.dto.request;

import com.example.posassist.entities.Items;
import lombok.Data;

@Data
public class OrderItemsDTO {
    private Items items;

    private String instructions;

    private Integer quantity;
}