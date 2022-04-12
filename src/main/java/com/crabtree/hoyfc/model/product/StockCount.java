package com.crabtree.hoyfc.model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockCount {
	private Integer currentStock;
	private Integer restockTrigger;
}