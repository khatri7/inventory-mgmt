package com.practice.inventory.api;

import com.practice.inventory.event.domain.MessageResponse;
import com.practice.inventory.model.Item;
import com.practice.inventory.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.http.ResponseEntity;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    @Output(value = "output") // The value should match the spring.cloud.stream.bindings.<<output>> value
    private MessageChannel channel;

//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    private static final String TOPIC = "KafkaExample";

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

    @GetMapping("publish/{message}")
    public String postMessage(@PathVariable("message") String message) {
        MessageResponse msgResponse = new MessageResponse(1l, message, "Abhishek Khatri");
        channel.send(MessageBuilder.withPayload(msgResponse).build());
        return "Message published";
    }
}
