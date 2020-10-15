package com.example.posassist.services;

import com.example.posassist.dto.request.RecipeDTO;
import com.example.posassist.entities.Ingredient;
import com.example.posassist.entities.Inventory;
import com.example.posassist.entities.Item;
import com.example.posassist.entities.Recipe;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.RecipeRepository;
import com.example.posassist.services.interfaces.IngredientService;
import com.example.posassist.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
public class RecipeServiceImpl implements com.example.posassist.services.interfaces.RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ItemService itemService;

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
    public Recipe findByName(String name) {
        Optional<Recipe> recipe = recipeRepository.findRecipeByRecipeName(name);
        if(!recipe.isPresent())
            throw new ResourceNotFoundException("This recipe does not exist");
        return recipe.get();
    }

    @Override
    public Recipe findByItem(Long itemId) {
        Item item = itemService.findItemById(itemId);
        Optional<Recipe> recipe = recipeRepository.findRecipeByItem(item);
        if(!recipe.isPresent()) {
            System.out.println("Item is not found");
            throw new ResourceNotFoundException("This recipe does not exist");
        }
        System.out.println("Item is found");
        return recipe.get();
    }

    @Override
    @Transactional
    public Recipe addNewRecipe(RecipeDTO recipeDTO) {

        Set<Ingredient> ingredientSet = new HashSet<>();
        recipeDTO.getIngredientQuantity().forEach(ingredientQuantityDTO ->
                ingredientSet.add(ingredientService.addNewIngredient(ingredientQuantityDTO)));

        Item item = itemService.findItemById(recipeDTO.getItemId());

        Recipe recipe = Recipe.builder()
                .recipeName(recipeDTO.getRecipeName())
                .ingredientQuantities(ingredientSet)
                .item(item)
                .build();

        return recipeRepository.save(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepository.delete(findById(id));
    }

    @Override
    public Map<Long, Double> ingredientQuantityMap(Recipe recipe) {
        Map<Long, Double> ingredientsQuantities = new HashMap<>();
        Set<Ingredient> ingredientSet = recipe.getIngredientQuantities();

        ingredientSet.forEach(ingredient -> {
            Long inventoryId = ingredient.getInventory().getId();
            Double quantity = ingredient.getQuantity();
            ingredientsQuantities.merge(inventoryId, quantity, Double::sum);
        });

        return ingredientsQuantities;
    }
}
