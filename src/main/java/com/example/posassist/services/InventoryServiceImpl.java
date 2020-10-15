package com.example.posassist.services;

import com.example.posassist.dto.request.InventoryDTO;
import com.example.posassist.entities.Inventory;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.InventoryRepository;
import com.example.posassist.services.interfaces.IngredientService;
import com.example.posassist.services.interfaces.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Inventory> allInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryItemById(Long id) {
        Optional<Inventory> ingredient = inventoryRepository.findById(id);
        if(!ingredient.isPresent())
            throw new ResourceNotFoundException("This item is not present in inventory");
        return ingredient.get();
    }

    @Override
    @Transactional
    public Inventory createInventoryItem(InventoryDTO inventoryDTO) {
        Inventory inventory = Inventory.builder()
                .ingredientName(inventoryDTO.getIngredientName())
                .cost(inventoryDTO.getCost())
                .quantity(inventoryDTO.getQuantity())
                .build();

        return inventoryRepository.save(inventory);
    }

    //TODO : update inventory after raw materials are consumed.

    @Override
    @Transactional
    public void deleteInventoryItem(Long id) {
        inventoryRepository.delete(getInventoryItemById(id));
    }
}
