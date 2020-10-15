package com.example.posassist.dto.request;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RecipeDTO {
    private String recipeName;

    private Set<IngredientDTO> ingredientQuantity = new HashSet<>();
}
