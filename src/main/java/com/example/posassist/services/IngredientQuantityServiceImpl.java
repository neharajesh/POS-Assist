package com.example.posassist.services;

import com.example.posassist.dto.request.IngredientQuantityDTO;
import com.example.posassist.entities.IngredientQuantity;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.IngredientQuantityRepository;
import com.example.posassist.services.interfaces.IngredientQuantityService;
import com.example.posassist.services.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientQuantityServiceImpl implements IngredientQuantityService {
    @Autowired
    private IngredientQuantityRepository ingredientQuantityRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<IngredientQuantity> findAllIngredients() {
        return ingredientQuantityRepository.findAll();
    }

    @Override
    public IngredientQuantity findIngredientById(Long id) {
        Optional<IngredientQuantity> ingredient = ingredientQuantityRepository.findById(id);
        if(!ingredient.isPresent())
            throw new ResourceNotFoundException("Ingredient not found!");
        return ingredient.get();
    }

    @Override
    @Transactional
    public IngredientQuantity addNewIngredient(IngredientQuantityDTO ingredientQuantityDTO) {
        IngredientQuantity ingredientQuantity = IngredientQuantity.builder()
                .ingredient(ingredientService.getIngredientById(ingredientQuantityDTO.getIngredientId()))
                .quantity(ingredientQuantityDTO.getQuantity())
                .build();

        return ingredientQuantityRepository.save(ingredientQuantity);
    }

    @Override
    @Transactional
    public void deleteIngredient(Long id) {
        ingredientQuantityRepository.delete(findIngredientById(id));
    }

}
