package com.crabtree.hoyfc.model.product.createProduct;

import com.crabtree.hoyfc.model.product.*;
import lombok.Data;

@Data
public class CreateProductFormData {
	// product sku's going to have a generation function for making skus as and when
	// same with the product QR
	private String productName;
	private String productDescription;
	private ProductColour productColour;
	private ProductType productType;
	private Integer stockCount;
	private Integer restockTrigger;
	private ProductStatus productStatus;
	private Double productPrice;
	// todo: handle the image somehow?

	public CreateProductParameters toParameters() {
		return new CreateProductParameters(
				ProductSku.createSku(productType, productName, productColour),
				productType,
				new ProductName(productName),
				new StockCount(stockCount, restockTrigger),
				new ProductDescription(productDescription, productColour),
				productStatus,
				new ProductPrice(productPrice)
		);
	}

}