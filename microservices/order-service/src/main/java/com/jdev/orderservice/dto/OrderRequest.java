package com.jdev.orderservice.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class OrderRequest {
	List<OrderLineItemDto> orderLineItemDtos;
}
