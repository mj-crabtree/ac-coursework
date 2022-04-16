package com.crabtree.hoyfc.model.product.editProduct;

import com.crabtree.hoyfc.model.product.*;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
import com.crabtree.hoyfc.model.product.createProduct.ProductPrice;

public class EditProductParameters extends CreateProductParameters {
	public EditProductParameters(Integer productId, ProductSku productSku, ProductType productType, ProductName productName, StockCount stockCount, ProductDescription productDescription, ProductStatus productStatus, ProductPrice productPrice) {
		super(productId, productSku, productType, productName, stockCount, productDescription, productStatus, productPrice);
	}
}