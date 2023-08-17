package com.jdev.orderservice.dto;

import java.util.List;

import com.jdev.orderservice.models.OrderLineItem;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderResponse {
	private long id;
	private String orderNumber;
	private List<OrderLineItem> orderLineItems;
}
