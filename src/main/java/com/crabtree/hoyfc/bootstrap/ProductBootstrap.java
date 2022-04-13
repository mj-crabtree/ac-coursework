package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.product.*;
import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
import com.crabtree.hoyfc.model.product.createProduct.ProductPrice;
import com.crabtree.hoyfc.service.ProductService;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProductBootstrap implements CommandLineRunner {

	private final ProductService productService;
	private final Faker faker;

	public ProductBootstrap(ProductService productService, Faker faker) {
		this.productService = productService;
		this.faker = faker;
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 100; i++) {
			CreateProductParameters parameters = newRandomProductParameters();
			productService.createProduct(parameters);
		}
	}

	private CreateProductParameters newRandomProductParameters() {
		var productName = faker
				.commerce()
				.productName();

		var productType = ProductType.getRandomProductType();

		var currentStockCount = faker
				.number()
				.numberBetween(5, 150);

		var restockTrigger = faker
				.number()
				.numberBetween(1, 5);

		var productDescriptionString = faker
				.lorem()
				.sentence();

		var productColour = new ProductColour(faker
				.color()
				.name());

		var productSku = ProductSku.createSku(productType, productName, productColour);

		var productStatus = ProductStatus.getRandomProductType();

		var productPrice = faker
				.number()
				.randomDouble(2, 1, 10);

		return new CreateProductParameters(
				productSku,
				productType,
				new ProductName(productName),
				new StockCount(currentStockCount, restockTrigger),
				new ProductDescription(productDescriptionString, productColour),
				productStatus,
				new ProductPrice(productPrice));
	}
}