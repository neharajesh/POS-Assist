package com.example.posassist.services;

import com.example.posassist.dto.request.InventoryDTO;
import com.example.posassist.entities.Inventory;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.InventoryRepository;
import com.example.posassist.services.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements com.example.posassist.services.interfaces.InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private IngredientService ingredientService;

    @Override
    public List<Inventory> allInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory getInventoryById(Long id) {
        Optional<Inventory> inventory = inventoryRepository.findById(id);
        if(!inventory.isPresent())
            throw new ResourceNotFoundException("This item is not present in inventory");
        return inventory.get();
    }

    @Override
    @Transactional
    public Inventory saveInventoryItem(InventoryDTO inventoryDTO) {
        Inventory inventory = Inventory.builder()
                .ingredient(ingredientService.findIngredientById(inventoryDTO.getIngredientId()))
                .cost(inventoryDTO.getCost())
                .quantityBought(inventoryDTO.getQuantityBought())
                .build();

        return inventoryRepository.save(inventory);
    }

    //TODO : update inventory after raw materials are consumed.

    @Override
    @Transactional
    public void deleteInventoryItem(Long id) {
        inventoryRepository.delete(getInventoryById(id));
    }
}
