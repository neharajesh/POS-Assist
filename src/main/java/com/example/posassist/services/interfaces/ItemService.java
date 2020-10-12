package com.example.posassist.services.interfaces;

import com.example.posassist.dto.request.ItemDTO;
import com.example.posassist.entities.Item;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemService {
    List<Item> getAllItems();

    Item findItemById(Long id);

    List<Item> findByAvailability(Boolean availability);

    Item findByItemName(String itemName);

    @Transactional
    Item saveNewItem(ItemDTO itemDTO);

    @Transactional
    Item updateItem(Long id, ItemDTO itemDTO);

    void deleteItem(Long id);
}
