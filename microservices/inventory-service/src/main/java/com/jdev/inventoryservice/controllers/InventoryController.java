package com.jdev.inventoryservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdev.inventoryservice.dto.InventoryResponse;
import com.jdev.inventoryservice.services.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("inventory")
public class InventoryController {

	private final InventoryService inventoryService;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<InventoryResponse> areInStock(@RequestParam("sku-code") List<String> skuCodes) {
		return inventoryService.areInStock(skuCodes);
	}
}
