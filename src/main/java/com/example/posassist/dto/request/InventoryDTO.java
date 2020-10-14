package com.example.posassist.dto.request;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long ingredientId;

    private Double cost;

    private Double quantityBought;
}
