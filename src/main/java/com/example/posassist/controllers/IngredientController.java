package com.example.posassist.controllers;

import com.example.posassist.dto.request.IngredientDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private IngredientService ingredientService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(ingredientService.findAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientService.findIngredientById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> getByName(@PathVariable String name) {
        return new ResponseEntity<>(ingredientService.findIngredientByName(name), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(ingredientService.addNewIngredient(ingredientDTO), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> updateIngredient(@PathVariable Long id, @RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(ingredientService.updateQuantity(ingredientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
