package com.practice.inventory.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id;

    @NotBlank(message = "Invalid Item Name")
    private String itemName;

    @NotBlank(message = "Invalid Item Description")
    private String itemDesc;

    @NotBlank(message = "Invalid Item Price")
    private Float itemPrice;

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Item(String itemName, String itemDesc, Float itemPrice) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemPrice = itemPrice;
    }
}
