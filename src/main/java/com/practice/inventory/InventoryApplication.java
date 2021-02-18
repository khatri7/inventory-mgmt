package com.practice.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 *
 * This is the Application class for the Inventory Microservice
 *
 * @author Abhishek Khatri
 */

@SpringBootApplication
@EnableBinding({ Sink.class, Source.class })
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
