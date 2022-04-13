package com.crabtree.hoyfc.model.product;

import java.util.List;
import java.util.Random;

public enum ProductType {

	PIN, PATCH, STICKER;

	private static final List<ProductType> TYPES = List.of(values());
	private static final Integer SIZE = TYPES.size();
	public static ProductType getRandomProductType() {
		var random = new Random();
		return TYPES.get(random.nextInt(SIZE));
	}
}