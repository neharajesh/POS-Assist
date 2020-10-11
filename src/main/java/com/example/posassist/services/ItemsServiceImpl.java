package com.example.posassist.services;

import com.example.posassist.dto.request.ItemsDTO;
import com.example.posassist.entities.Items;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.ItemsRepository;
import com.example.posassist.services.interfaces.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public List<Items> getAllItems() {
        return itemsRepository.findAll();
    }

    @Override
    public Items findItemById(Long id) {
        Optional<Items> item = itemsRepository.findById(id);
        if(!item.isPresent())
            throw new ResourceNotFoundException("This item is not on the menu tonight!");
        return item.get();
    }

    @Override
    public List<Items> findByAvailability(Boolean availability) {
        return itemsRepository.findByAvailability(availability);
    }

    @Override
    public Items findByItemName(String itemName) {
        Optional<Items> item= itemsRepository.findByItemName(itemName);
        if(!item.isPresent())
            throw new ResourceNotFoundException("This item does not exist!");
        return item.get();
    }

    @Override
    @Transactional
    public Items saveNewItem(ItemsDTO itemsDTO) {
        Items item = Items.builder()
                .itemName(itemsDTO.getItemName())
                .itemType(itemsDTO.getItemType())
                .price(itemsDTO.getPrice())
                .availability(itemsDTO.getAvailability())
                .build();
        return itemsRepository.save(item);
    }

    @Override
    @Transactional
    public Items updateItem(Long id, ItemsDTO itemsDTO) {
        Items item = findItemById(id);
        item.setItemName(itemsDTO.getItemName());
        item.setItemType(itemsDTO.getItemType());
        item.setPrice(itemsDTO.getPrice());
        item.setAvailability(itemsDTO.getAvailability());
        return itemsRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        Items item = findItemById(id);
        itemsRepository.delete(item);
    }
}
