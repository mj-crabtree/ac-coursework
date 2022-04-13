package com.crabtree.hoyfc.model.product;

import lombok.Data;

@Data
public class ProductSku {
	public static ProductSku createSku(ProductType productType, String productName, ProductColour productColour) {
		return new ProductSku();
	}
}