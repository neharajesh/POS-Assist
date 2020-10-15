package com.example.posassist.dto.request;

import lombok.Data;

@Data
public class InventoryDTO {
    private String ingredientName;

    private Double cost;

    private Double quantity;
}
