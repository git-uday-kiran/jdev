package com.jdev.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jdev.inventoryservice.models.Inventory;
import com.jdev.inventoryservice.repositories.InventoryRepository;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String... args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_12");
			inventory.setQuantity(20);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_13");
			inventory2.setQuantity(0);
 
			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory2);
		};
	}
}
