package com.jdev.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdev.productservice.dto.ProductRequest;
import com.jdev.productservice.dto.ProductResponse;
import com.jdev.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@RestController
@RequestMapping("product")
public class ProductController {
	
	private final ProductService productService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String createProduct(@RequestBody ProductRequest productRequest) {
		productService.createProduct(productRequest);
		return "product is created";
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK) 
	public List<ProductResponse> getAllProducts() {
		return productService.getAllProducts();
	}
}  
