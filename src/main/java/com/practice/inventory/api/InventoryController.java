package com.practice.inventory.api;

import com.practice.inventory.model.Inventory;
import com.practice.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping
    public ResponseEntity<?> addInventory(@RequestBody Inventory newInventory) {
        return inventoryService.addInventory(newInventory);
    }
}
