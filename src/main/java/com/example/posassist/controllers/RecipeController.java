package com.example.posassist.controllers;

import com.example.posassist.dto.request.RecipeDTO;
import com.example.posassist.services.interfaces.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(RecipeController.BASE_URL)
public class RecipeController {
    public static final String BASE_URL = "/api/v1/recipe";

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(recipeService.allRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<?> addInventory(@RequestBody RecipeDTO recipeDTO) {
        return new ResponseEntity<>(recipeService.addNewRecipe(recipeDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteInventory (@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
