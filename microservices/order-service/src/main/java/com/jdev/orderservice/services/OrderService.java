package com.jdev.orderservice.services;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.jdev.orderservice.dto.InventoryResponse;
import com.jdev.orderservice.dto.OrderLineItemDto;
import com.jdev.orderservice.dto.OrderRequest;
import com.jdev.orderservice.dto.OrderResponse;
import com.jdev.orderservice.models.Order;
import com.jdev.orderservice.models.OrderLineItem;
import com.jdev.orderservice.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;

	private final WebClient webClient;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = Order.builder()
				.orderNumber(UUID.randomUUID().toString())
				.orderLineItems(orderRequest.getOrderLineItemDtos()
						.parallelStream()
						.map(this::mapToModel)
						.toList())
				.build();
		
		List<String> skuCodes = order.getOrderLineItems().stream()
				.map(OrderLineItem::getSkuCode)
				.toList();
		
		String uri = "http://inventory-service/api/inventory";
		Function<UriBuilder, URI> uriBuilderFunction = builder -> builder.queryParam("sku-code", skuCodes).build();
		
		InventoryResponse[] inventoryResponses = webClient.get()
				.uri(uri, uriBuilderFunction)
				.retrieve()
				.bodyToMono(InventoryResponse[].class)
				.block();
		
		boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.isInStock());
		
		if (!allProductsInStock)
			throw new IllegalArgumentException("product is not in the stock, please try again later.");
			
		orderRepository.save(order);
		log.info("order {} is saved", order.getOrderNumber());
	}
	
	public List<OrderResponse> findAll() {
		return orderRepository.findAll()
				.parallelStream()
				.map(this::mapToResponse)
				.toList();
	}
	
	public OrderLineItem mapToModel(OrderLineItemDto orderLineItemDto) {
		return OrderLineItem.builder()
				.id(orderLineItemDto.getId())
				.price(orderLineItemDto.getPrice())
				.skuCode(orderLineItemDto.getSkuCode())
				.quantity(orderLineItemDto.getQuantity())
				.build();
	}
	
	public OrderLineItemDto mapToDto(OrderLineItem orderLineItem) {
		return OrderLineItemDto.builder()
				.id(orderLineItem.getId())
				.skuCode(orderLineItem.getSkuCode())
				.price(orderLineItem.getPrice())
				.quantity(orderLineItem.getQuantity())
				.build();
	}
	
	public OrderResponse mapToResponse(Order order) {
		return OrderResponse.builder()
				.id(order.getId())
				.orderNumber(order.getOrderNumber())
				.orderLineItems(order.getOrderLineItems())
				.build();
	}

}
