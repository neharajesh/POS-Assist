package com.example.posassist.repositories;

import com.example.posassist.entities.IngredientQuantity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientQuantityRepository extends JpaRepository<IngredientQuantity, Long> {

}
