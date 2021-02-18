package com.practice.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "inventories")
public class Inventory {
    @Id
    private String inventoryId;
    private String inventoryName;
    private String inventoryAddress;
//    @JsonIgnore
    private InventoryItem[] inventoryItems;

    public String getInventoryId() {
        return inventoryId;
    }

    public InventoryItem[] getInventoryItems() {
        return inventoryItems;
    }

    public Inventory(String inventoryName, String inventoryAddress, InventoryItem[] inventoryItems) {
        this.inventoryName = inventoryName;
        this.inventoryAddress = inventoryAddress;
        this.inventoryItems = inventoryItems;
    }
}
