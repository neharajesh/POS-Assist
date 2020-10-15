package com.example.posassist.dto.request;

import lombok.Data;

@Data
public class IngredientDTO {
    private Long inventoryId;

    private Double quantity;
}
