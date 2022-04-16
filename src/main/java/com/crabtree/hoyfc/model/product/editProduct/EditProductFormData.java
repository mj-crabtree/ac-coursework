package com.crabtree.hoyfc.model.product.editProduct;

import com.crabtree.hoyfc.model.product.*;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductFormData;
import com.crabtree.hoyfc.model.product.createProduct.ProductPrice;
import lombok.Data;

@Data
public class EditProductFormData extends CreateProductFormData {
	private Integer id;

	public static EditProductFormData fromProduct(Product product) {
		EditProductFormData result = new EditProductFormData();

		result.setId(product.getId());
		result.setSku(product.getProductSku().getProductSku());
		result.setProductName(product.getProductName().getProductName());
		result.setProductType(product.getProductType());
		result.setProductStatus(product.getProductStatus());
		result.setProductDescription(product.getProductDescription().getProductDescription());

		result.setProductColour(product
				.getProductDescription()
				.getProductColour()
				.getProductColour());

		result.setProductPrice(product
				.getProductPrice()
				.getProductPrice());

		result.setStockCount(product
				.getStockCount()
				.getCurrentStock());

		result.setRestockTrigger(product
				.getStockCount()
				.getRestockTrigger());

		return result;
	}

	public EditProductParameters toParameters() {
		return new EditProductParameters(
				getId(),
				new ProductSku(getSku()),
				getProductType(),
				new ProductName(getProductName()),
				new StockCount(getStockCount(), getRestockTrigger()),
				new ProductDescription(getProductDescription(), new ProductColour(getProductColour())),
				getProductStatus(),
				new ProductPrice(getProductPrice())

		);
	}
}