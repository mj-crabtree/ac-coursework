package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.product.createProduct.CreateProductParameters;
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
		CreateProductParameters parameters = newRandomProductParameters();
		productService.createProduct(parameters);
	}

	private CreateProductParameters newRandomProductParameters() {
		return null;
	}
}