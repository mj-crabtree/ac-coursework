package com.crabtree.hoyfc.model.product;

import lombok.Data;

@Data
public class StockCount {
	private Integer currentStock;
	private Integer restockTrigger;
}