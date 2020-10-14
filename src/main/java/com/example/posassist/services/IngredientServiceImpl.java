package com.example.posassist.services;

import com.example.posassist.dto.request.IngredientDTO;
import com.example.posassist.entities.Ingredient;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.IngredientRepository;
import com.example.posassist.services.interfaces.IngredientQuantityService;
import com.example.posassist.services.interfaces.IngredientService;
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
    private IngredientQuantityService ingredientQuantityService;

    @Override
    public List<Ingredient> allInventoryItems() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(!ingredient.isPresent())
            throw new ResourceNotFoundException("This item is not present in inventory");
        return ingredient.get();
    }

    @Override
    @Transactional
    public Ingredient createIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = Ingredient.builder()
                .ingredientName(ingredientDTO.getIngredientName())
                .cost(ingredientDTO.getCost())
                .build();

        return ingredientRepository.save(ingredient);
    }

    //TODO : update inventory after raw materials are consumed.

    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        ingredientRepository.delete(getIngredientById(id));
    }
}
