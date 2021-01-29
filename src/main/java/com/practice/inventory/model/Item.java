package com.practice.inventory.model;

//import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String itemName;
    private String itemDesc;
    private Float price;

    public Item(String itemName, String itemDesc, Float price) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public Float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format(
                "Item[id=%s, itemName='%s', itemDesc='%s', itemPrice=%s]",
                id, itemName, itemDesc, price);
    }
}
