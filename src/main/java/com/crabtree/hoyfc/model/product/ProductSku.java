package com.crabtree.hoyfc.model.product;

import lombok.Data;

@Data
public class ProductSku {
	public static ProductSku createSku(ProductType productType, String productName, String productColour) {
		return new ProductSku();
	}
}