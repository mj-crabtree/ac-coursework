package com.crabtree.hoyfc.model.product.createProduct;

import com.crabtree.hoyfc.model.product.*;
import com.crabtree.hoyfc.service.product.ProductIdService;
import com.crabtree.hoyfc.service.product.ProductSkuService;
import lombok.Data;

@Data
public class CreateProductFormData {
	public String productName;
	private String sku;
	private Double productPrice;
	private Integer stockCount;
	// private Integer secondsCount
	private Integer restockTrigger;
	private String productDescription;
	private String productColour;
	private ProductType productType;
	private ProductStatus productStatus;

	public CreateProductParameters toParameters() {
		var sku = new ProductSkuService();
		var newProductId = ProductIdService.getNextId();
		var newProductName = new ProductName(this.productName);
		var newStockCount = new StockCount(this.stockCount, this.restockTrigger);
		var newProductColour = new ProductColour(this.productColour);
		var newProductDescription = new ProductDescription(this.productDescription, newProductColour);
		var newProductPrice = new ProductPrice(this.productPrice);
		var newProductSku = sku.createSku(this.productType, this.productName, newProductColour);

		return new CreateProductParameters(newProductId, newProductSku, productType, newProductName, newStockCount, newProductDescription, productStatus, newProductPrice);
	}

}