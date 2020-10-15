package com.example.posassist.controllers;

import com.example.posassist.dto.request.InventoryDTO;
import com.example.posassist.services.interfaces.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(InventoryController.BASE_URL)
public class InventoryController {
    public static final String BASE_URL = "/api/v1/inventory";

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(inventoryService.allInventoryItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryService.getInventoryItemById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody
    ResponseEntity<?> addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(inventoryService.createInventoryItem(inventoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody
    ResponseEntity<?> deleteInventory (@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return new ResponseEntity<>("Deleted successfully!", HttpStatus.OK);
    }
}
