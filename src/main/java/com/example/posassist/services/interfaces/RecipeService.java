package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.RecipeDTO;
import com.example.posassist.entities.Recipe;

import javax.transaction.Transactional;
import java.util.List;

public interface RecipeService {
    List<Recipe> allRecipes();

    Recipe findById(Long id);

    @Transactional
    Recipe addNewRecipe(RecipeDTO recipeDTO);

    @Transactional
    void deleteRecipe(Long id);
}
