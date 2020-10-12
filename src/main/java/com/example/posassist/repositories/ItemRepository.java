package com.example.posassist.repositories;

import com.example.posassist.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemName(String itemName);

    List<Item> findByAvailability(Boolean availability);
}
