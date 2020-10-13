package com.example.posassist.controllers;

import com.example.posassist.dto.request.ItemDTO;
import com.example.posassist.services.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*", maxAge=3600)
@RestController
@RequestMapping(ItemController.BASE_URL)
public class ItemController {
    public static final String BASE_URL = "/api/v1/items";

    @Autowired
    private ItemService itemService;

    @GetMapping
    public @ResponseBody
    ResponseEntity<?> getAll() {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.findItemById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public @ResponseBody
    ResponseEntity<?> getByName(@PathVariable String name) {
        return new ResponseEntity<>(itemService.findByItemName(name), HttpStatus.OK);
    }

    @GetMapping("/{availability}")
    public @ResponseBody
    ResponseEntity<?> getByAvailability(@PathVariable Boolean availability) {
        return new ResponseEntity<>(itemService.findByAvailability(availability), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.saveNewItem(itemDTO), HttpStatus.OK);
    }

    @PostMapping("/updateItem/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public @ResponseBody
    ResponseEntity<?> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        return new ResponseEntity<>(itemService.updateItem(id, itemDTO), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody
    ResponseEntity<?> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("The item has been deleted successfully!", HttpStatus.OK);
    }
}