package com.example.posassist.repositories;

import com.example.posassist.entities.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {
    Optional<Items> findByItemName(String itemName);

    List<Items> findByAvailability(Boolean availability);
}
