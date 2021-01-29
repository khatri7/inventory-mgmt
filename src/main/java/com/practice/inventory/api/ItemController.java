package com.practice.inventory.api;

import com.practice.inventory.model.Item;
import com.practice.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Item> getAllItems() {
        return itemService.getAllItems();
//        if(itemList.size() > 0) {
//            return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity<>("{ \"error\" : \"No items f}", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
    }
}
