// package com.crabtree.hoyfc.bootstrap;
//
// import com.crabtree.teadatabase.model.product.CreateProductParameters;
// import com.crabtree.teadatabase.model.product.ProductColour;
// import com.crabtree.teadatabase.model.product.ProductName;
// import com.crabtree.teadatabase.model.product.ProductType;
// import com.crabtree.teadatabase.service.ProductService;
// import com.github.javafaker.Commerce;
// import com.github.javafaker.Faker;
// import com.github.javafaker.service.RandomService;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Profile;
// import org.springframework.stereotype.Component;
//
// @Component
// @Profile("init-db")
// public class ProductBootstrap implements CommandLineRunner {
// 	private final ProductService productService;
// 	private final Faker faker;
//
// 	public ProductBootstrap(ProductService productService, Faker faker) {
// 		this.productService = productService;
// 		this.faker = faker;
// 	}
//
// 	@Override
// 	public void run(String... args) throws Exception {
// 		for (int i = 0; i < 20; i++) {
// 			CreateProductParameters parameters = newRandomProductParameters();
// 			productService.createProduct(parameters);
// 		}
// 	}
//
// 	private CreateProductParameters newRandomProductParameters() {
//
// 		Commerce product = faker.commerce();
//
// 		ProductName productName = new ProductName(faker.elderScrolls()
// 				.creature());
// 		ProductType productType = getRandomProductType();
// 		ProductColour productColour = getRandomProductColour();
// 		Integer stockCount = getRandomStockCount();
// 		Integer restockTrigger = getRandomRestockTrigger();
// 		boolean archived = faker.bool()
// 				.bool();
// 		boolean inStock = stockCount > 0;
// 		double price = Double.parseDouble(product.price(0.99, 5.00));
//
// 		return new CreateProductParameters(productName, productType, productColour, stockCount, restockTrigger, archived, inStock, price);
// 	}
//
// 	private double getRandomProductPrice() {
// 		RandomService random = faker.random();
// 		return random.nextInt(1, 10);
// 	}
//
// 	private ProductType getRandomProductType() {
// 		RandomService random = faker.random();
// 		ProductType[] productTypes = ProductType.values();
// 		return productTypes[random.nextInt(productTypes.length)];
// 	}
//
// 	private ProductColour getRandomProductColour() {
// 		RandomService random = faker.random();
// 		ProductColour[] productColours = ProductColour.values();
// 		return productColours[random.nextInt(productColours.length)];
// 	}
//
// 	private Integer getRandomStockCount() {
// 		RandomService random = faker.random();
// 		return random.nextInt(20, 100);
// 	}
//
// 	private Integer getRandomRestockTrigger() {
// 		RandomService random = faker.random();
// 		return random.nextInt(5, 10);
// 	}
// }