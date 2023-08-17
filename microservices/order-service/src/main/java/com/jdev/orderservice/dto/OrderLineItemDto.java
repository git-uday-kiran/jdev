package com.jdev.orderservice.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderLineItemDto {
	private long id;
	private String skuCode;
	private BigDecimal price;
	private long quantity;
}
