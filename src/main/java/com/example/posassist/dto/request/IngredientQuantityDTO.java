package com.example.posassist.dto.request;

import lombok.Data;

@Data
public class IngredientQuantityDTO {
    private Long ingredientId;

    private Double quantity;
}
