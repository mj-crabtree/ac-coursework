package com.crabtree.hoyfc.model.product;

import lombok.Data;

@Data
public class ProductSku {
	private String productSku;
	public static ProductSku createSku(ProductType productType, String productName, ProductColour productColour) {
		return new ProductSku();
	}
}