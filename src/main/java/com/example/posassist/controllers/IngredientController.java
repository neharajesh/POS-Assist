package com.example.posassist.controllers;

import com.example.posassist.dto.request.IngredientDTO;
import com.example.posassist.services.interfaces.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(IngredientController.BASE_URL)
public class IngredientController {
    public static final String BASE_URL = "/api/v1/ingredient";

    @Autowired
    private IngredientService inventoryService;

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(inventoryService.allInventoryItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getIngredientById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<?> addInventory(@RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(inventoryService.createIngredient(ingredientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteInventory (@PathVariable Long id) {
        inventoryService.deleteIngredient(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
