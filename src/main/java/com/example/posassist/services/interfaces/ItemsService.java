package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.ItemsDTO;
import com.example.posassist.entities.Items;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemsService {
    List<Items> getAllItems();

    Items findItemById(Long id);

    List<Items> findByAvailability(Boolean availability);

    Items findByItemName(String itemName);

    @Transactional
    Items saveNewItem(ItemsDTO itemsDTO);

    @Transactional
    Items updateItem(Long id, ItemsDTO itemsDTO);

    void deleteItem(Long id);
}
