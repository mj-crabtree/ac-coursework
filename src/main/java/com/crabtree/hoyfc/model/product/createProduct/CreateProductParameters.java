package com.crabtree.hoyfc.model.product.createProduct;

import com.crabtree.hoyfc.model.product.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateProductParameters {
	private final Integer productId;
	private final ProductSku productSku;
	private final ProductType productType;
	private final ProductName productName;
	private final StockCount stockCount;
	private final ProductDescription productDescription;
	private final ProductStatus productStatus;
	private final ProductPrice productPrice;
	// private final ProductImage productImage;
	// private final ProductQr productQr;
}