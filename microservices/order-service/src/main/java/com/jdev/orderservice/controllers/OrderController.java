package com.jdev.orderservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jdev.orderservice.dto.OrderRequest;
import com.jdev.orderservice.dto.OrderResponse;
import com.jdev.orderservice.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public String placeOrer(@RequestBody OrderRequest orderRequest) {
		orderService.placeOrder(orderRequest);
		return "Order is placed";
	}
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<OrderResponse> findAll() {
		return orderService.findAll();
	}
}
