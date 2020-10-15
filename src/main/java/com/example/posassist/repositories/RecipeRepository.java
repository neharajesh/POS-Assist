package com.example.posassist.repositories;

import com.example.posassist.entities.Item;
import com.example.posassist.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findRecipeByRecipeName(String recipeName);
    Optional<Recipe> findRecipeByItem(Item item);
}
