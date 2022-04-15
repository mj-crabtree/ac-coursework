package com.crabtree.hoyfc.model.order;

import java.util.List;
import java.util.Random;

public enum ShippingType {

	EXPEDITED, FIRST_CLASS, SATURDAY, SECOND_CLASS;
	private static final List<ShippingType> types = List.of(values());

	public static ShippingType getRandomShippingType() {
		var random = new Random();
		return types.get(random.nextInt(types.size()));
	}
}