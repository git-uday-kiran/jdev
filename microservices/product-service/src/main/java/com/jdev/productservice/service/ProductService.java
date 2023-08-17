package com.jdev.productservice.service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jdev.productservice.dto.ProductRequest;
import com.jdev.productservice.dto.ProductResponse;
import com.jdev.productservice.model.Product;
import com.jdev.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public void createProduct(@RequestBody ProductRequest productRequest) {
		Product product = Product.builder()
								 .id(productRequest.getId())
								 .name(productRequest.getName())
								 .description(productRequest.getDescription())
								 .price(productRequest.getPrice()).build();
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts() {
		Iterable<Product> products = productRepository.findAll(); 
		Stream<Product> stream = StreamSupport.stream(products.spliterator(), true);
		return stream.map(this::mapToResponse).toList();
	}
	
	public ProductResponse mapToResponse(Product product) {
		ProductResponse productResponse = ProductResponse.builder()  
														 .id(product.getId())
														 .name(product.getName())  
														 .description(product.getDescription())
														 .price(product.getPrice()) 
														 .build();
		return productResponse;
	}
}


