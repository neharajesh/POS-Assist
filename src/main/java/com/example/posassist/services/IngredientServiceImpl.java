package com.example.posassist.services;

import com.example.posassist.dto.request.IngredientDTO;
import com.example.posassist.entities.Ingredient;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.IngredientRepository;
import com.example.posassist.services.interfaces.IngredientService;
import com.example.posassist.services.interfaces.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient findIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(!ingredient.isPresent())
            throw new ResourceNotFoundException("Inventory not found!");
        return ingredient.get();
    }

    @Override
    @Transactional
    public Ingredient addNewIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = Ingredient.builder()
                .inventory(inventoryService.getInventoryItemById(ingredientDTO.getInventoryId()))
                .quantity(ingredientDTO.getQuantity())
                .build();

        return ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        ingredientRepository.delete(findIngredientById(id));
    }

}
