package com.practice.inventory.service;

import com.practice.inventory.repository.ItemRepository;
import com.practice.inventory.model.Item;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ResponseEntity<?> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        if(itemList.size() > 0){
            return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> createItem(Item newItem) {
        try {
            itemRepository.save(newItem);
            return new ResponseEntity<Item>(newItem, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateItemById(String id, Item newItem) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            Item itemToUpdate = item.get();
            try {
                itemToUpdate.setItemName(newItem.getItemName() != null ? newItem.getItemName() : itemToUpdate.getItemName());
                itemToUpdate.setItemDesc(newItem.getItemDesc() != null ? newItem.getItemDesc() : itemToUpdate.getItemDesc());
                itemToUpdate.setItemPrice(newItem.getItemPrice() != null ? newItem.getItemPrice() : itemToUpdate.getItemPrice());
                return new ResponseEntity<Item>(itemToUpdate, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()) {
            try {
                itemRepository.delete(item.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    public String getItemNameById(String id) {
//        String item = getItemById(id).getBody().toString();
//        String itemName = item.substring(item.indexOf("itemName")+9, item.indexOf("itemDesc")-3);
////        JSONObject itemNameObj = new JSONObject();
////        itemNameObj.put("itemName", itemName);
//        return itemName;
////        return new ResponseEntity<JSONObject>(itemNameObj, HttpStatus.OK);
//    }
}
