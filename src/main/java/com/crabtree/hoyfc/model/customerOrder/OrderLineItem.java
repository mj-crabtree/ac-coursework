package com.crabtree.hoyfc.model.customerOrder;

import com.crabtree.hoyfc.model.product.Product;
import lombok.Data;

@Data
public class OrderLineItem {
	private Integer orderId;
	private Product product;
	private Integer count;
	private Double totalCost;
}