package com.jdev.inventoryservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jdev.inventoryservice.dto.InventoryResponse;
import com.jdev.inventoryservice.models.Inventory;
import com.jdev.inventoryservice.repositories.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;
	
	public List<InventoryResponse> areInStock(List<String> skuCodes) {
		return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
				.map(this::mapToResponse)
				.toList();
	}
	
	public InventoryResponse mapToResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity() > 0)
				.build();
	}
}
