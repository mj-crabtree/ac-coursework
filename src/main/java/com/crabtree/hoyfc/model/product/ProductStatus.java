package com.crabtree.hoyfc.model.product;

import java.util.List;
import java.util.Random;

public enum ProductStatus {
	ACTIVE, ARCHIVE, DISCONTINUED;

	private static final List<ProductStatus> TYPES = List.of(values());
	private static final Integer SIZE = TYPES.size();

	public static ProductStatus getRandomProductType() {
		var random = new Random();
		return TYPES.get(random.nextInt(SIZE));
	}
}