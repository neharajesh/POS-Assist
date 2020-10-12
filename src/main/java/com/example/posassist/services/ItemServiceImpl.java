package com.example.posassist.services;

import com.example.posassist.dto.request.ItemDTO;
import com.example.posassist.entities.Item;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.ItemRepository;
import com.example.posassist.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if(!item.isPresent())
            throw new ResourceNotFoundException("This item is not on the menu tonight!");
        return item.get();
    }

    @Override
    public List<Item> findByAvailability(Boolean availability) {
        return itemRepository.findByAvailability(availability);
    }

    @Override
    public Item findByItemName(String itemName) {
        Optional<Item> item= itemRepository.findByItemName(itemName);
        if(!item.isPresent())
            throw new ResourceNotFoundException("This item does not exist!");
        return item.get();
    }

    @Override
    @Transactional
    public Item saveNewItem(ItemDTO itemDTO) {
        Item item = Item.builder()
                .itemName(itemDTO.getItemName())
                .itemType(itemDTO.getItemType())
                .price(itemDTO.getPrice())
                .availability(itemDTO.getAvailability())
                .build();
        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public Item updateItem(Long id, ItemDTO itemDTO) {
        Item item = findItemById(id);
        item.setItemName(itemDTO.getItemName());
        item.setItemType(itemDTO.getItemType());
        item.setPrice(itemDTO.getPrice());
        item.setAvailability(itemDTO.getAvailability());
        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Item item = findItemById(id);
        itemRepository.delete(item);
    }
}
