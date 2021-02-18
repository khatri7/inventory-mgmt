package com.practice.inventory.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Data
public class InventoryItem {
    private ObjectId itemId;
    private Integer qty;

    public String getItemId() {
        return itemId.toString();
    }

    public Integer getQty() {
        return qty;
    }
}
