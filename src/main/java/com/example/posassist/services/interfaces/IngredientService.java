package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.IngredientDTO;
import com.example.posassist.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    List<Ingredient> findAllIngredients();

    Ingredient findIngredientById(Long id);

    Ingredient addNewIngredient(IngredientDTO ingredientDTO);

    void deleteIngredient(Long id);
}
