package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.IngredientQuantityDTO;
import com.example.posassist.entities.IngredientQuantity;

import java.util.List;

public interface IngredientQuantityService {
    List<IngredientQuantity> findAllIngredients();

    IngredientQuantity findIngredientById(Long id);

    IngredientQuantity addNewIngredient(IngredientQuantityDTO ingredientQuantityDTO);

    void deleteIngredient(Long id);
}
