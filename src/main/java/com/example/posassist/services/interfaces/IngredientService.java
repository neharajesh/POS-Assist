package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.IngredientDTO;
import com.example.posassist.entities.Ingredient;

import javax.transaction.Transactional;
import java.util.List;

public interface IngredientService {
    List<Ingredient> allInventoryItems();

    Ingredient getIngredientById(Long id);

    @Transactional
    Ingredient createIngredient(IngredientDTO ingredientDTO);

    @Transactional
    void deleteIngredient(Long id);
}
