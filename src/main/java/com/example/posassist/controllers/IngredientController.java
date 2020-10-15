/*
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
@RequestMapping(IngredientQuantityController.BASE_URL)
public class IngredientQuantityController {
    public static final String BASE_URL = "/api/v1/ingredientquantity";

    @Autowired
    private IngredientService ingredientQuantityService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(ingredientQuantityService.findAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(ingredientQuantityService.findIngredientById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> addIngredient(@RequestBody IngredientDTO ingredientQuantityDTO) {
        return new ResponseEntity<>(ingredientQuantityService.addNewIngredient(ingredientQuantityDTO), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody ResponseEntity<?> deleteIngredient(@PathVariable Long id) {
        ingredientQuantityService.deleteIngredient(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
*/
