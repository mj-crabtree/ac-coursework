package com.crabtree.hoyfc.model.product;

import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import lombok.Data;

@Data
public class Product extends BaseEntity {
	private ProductSku productSku;
	private ProductType productType;
	private ProductName productName;
	private StockCount stockCount;
	private ProductDescription productDescription;
	private ProductImage productImage;
	private ProductQr productQr;
	private ProductStatus productStatus;
}