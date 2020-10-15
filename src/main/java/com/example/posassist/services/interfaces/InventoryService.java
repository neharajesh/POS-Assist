package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.InventoryDTO;
import com.example.posassist.entities.Inventory;

import javax.transaction.Transactional;
import java.util.List;

public interface InventoryService {
    List<Inventory> allInventoryItems();

    Inventory getInventoryItemById(Long id);

    @Transactional
    Inventory createInventoryItem(InventoryDTO inventoryDTO);

    @Transactional
    void deleteInventoryItem(Long id);
}