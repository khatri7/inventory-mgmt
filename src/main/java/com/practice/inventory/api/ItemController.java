package com.practice.inventory.api;

import com.practice.inventory.model.Item;
import com.practice.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Item newItem) {
        return itemService.createItem(newItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable("id") String id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItemById(@PathVariable("id") String id, @RequestBody Item newItem) {
        return itemService.updateItemById(id, newItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItemById(@PathVariable("id") String id) {
        return itemService.deleteItemById(id);
    }
}
