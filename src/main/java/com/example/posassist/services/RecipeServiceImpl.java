package com.example.posassist.services;

import com.example.posassist.dto.request.RecipeDTO;
import com.example.posassist.entities.IngredientQuantity;
import com.example.posassist.entities.Recipe;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.RecipeRepository;
import com.example.posassist.services.interfaces.IngredientQuantityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements com.example.posassist.services.interfaces.RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientQuantityService ingredientQuantityService;

    @Override
    public List<Recipe> allRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent())
            throw new ResourceNotFoundException("No recipe found!");
        return recipe.get();
    }

    @Override
    @Transactional
    public Recipe addNewRecipe(RecipeDTO recipeDTO) {

        Set<IngredientQuantity> ingredientQuantitySet = new HashSet<>();
        recipeDTO.getIngredientQuantity().forEach(ingredientQuantityDTO ->
                ingredientQuantitySet.add(ingredientQuantityService.addNewIngredient(ingredientQuantityDTO)));

        Recipe recipe = Recipe.builder()
                .recipeName(recipeDTO.getRecipeName())
                .ingredientQuantities(ingredientQuantitySet)
                .build();

        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.delete(findById(id));
    }

}
