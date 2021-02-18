package com.practice.inventory.service;

import com.practice.inventory.model.Inventory;
import com.practice.inventory.model.InventoryItem;
import com.practice.inventory.repository.InventoryRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private ItemService itemService;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, ItemService itemService) {
        this.inventoryRepository = inventoryRepository;
        this.itemService = itemService;
    }

    public ResponseEntity<?> getAllInventories() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        if(inventoryList.size() > 0) {
            return new ResponseEntity<List<Inventory>>(inventoryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    public ResponseEntity<?> getAllInventories() {
//        JSONObject testInvObj = new JSONObject();
//        JSONObject testItemsObj = new JSONObject();
//        JSONArray testInvArray = new JSONArray();
//        JSONArray testItemsArray = new JSONArray();
//
//
//        List<Inventory> inventoryList = inventoryRepository.findAll();
//        if(inventoryList.size() > 0) {
//
//
//            testInvArray.clear();
//            for(Inventory inventory : inventoryList) {
//                testItemsArray.clear();
//                for(InventoryItem inventoryItem : inventory.getInventoryItems()) {
//                    testItemsArray.add(itemService.getItemById(inventoryItem.getItemId()).getBody());
//                }
////                testInvArray.add(testItemsArray);
//
//                testItemsObj.put("items", testItemsArray);
//                testInvArray.add(testItemsObj);
//            }
//
//
////            testInvObj.put("inventories", testInvArray);
//
//
//            return new ResponseEntity<>(testInvArray, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    public ResponseEntity<?> getAvailableItemQty(String itemId) {
        int totalAvailableQty = 0;
        JSONObject totalQtyResponse = new JSONObject();
        totalQtyResponse.put("itemId", itemId);
        JSONArray itemQtyArray = new JSONArray();
        List<Inventory> inventoryList = inventoryRepository.findAll();
        for(Inventory inventory : inventoryList) {
            for(InventoryItem inventoryItem : inventory.getInventoryItems()) {
                if(inventoryItem.getItemId().equals(itemId)){
                    JSONObject inventoryObj = new JSONObject();
                    inventoryObj.put("inventoryId", inventory.getInventoryId());
                    inventoryObj.put("qty", inventoryItem.getQty());
                    totalAvailableQty+=inventoryItem.getQty();
                    itemQtyArray.add(inventoryObj);
                    break;
                }
            }
        }
        totalQtyResponse.put("totalQty", totalAvailableQty);
        totalQtyResponse.put("inventories", itemQtyArray);

        return new ResponseEntity<JSONObject>(totalQtyResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> addInventory(Inventory newInventory) {
        try {
            inventoryRepository.save(newInventory);
            return new ResponseEntity<Inventory>(newInventory, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
