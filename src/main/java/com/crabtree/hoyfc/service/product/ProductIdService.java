package com.crabtree.hoyfc.service.product;

import org.springframework.stereotype.Service;

@Service
public class ProductIdService {
	private static int productId = 0;
	private static int productSkuId = 0;

	public static int getNextId() {
		return ++productId;
	}

	public static int getNextProductSkuId() {
		return ++productSkuId;
	}
}