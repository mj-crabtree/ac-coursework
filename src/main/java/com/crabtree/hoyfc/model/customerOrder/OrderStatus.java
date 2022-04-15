package com.crabtree.hoyfc.model.customerOrder;

import java.util.List;
import java.util.Random;

public enum OrderStatus {
	CANCELLED, FULFILLED, PENDING, PICKED, PACKED, SHIPPED;

	private static final List<OrderStatus> types = List.of(values());

	public static OrderStatus getrandomOrderStatus() {
		var random = new Random();
		return types.get(random.nextInt(types.size()));
	}
}