package com.crabtree.hoyfc.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductRepositoryTest {
	private final ProductRepository productRepository;

	public ProductRepositoryTest(@Autowired ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Test
	@DisplayName("Get by index should return appropriate product")
	public void getByIndexShouldReturnAppropriateProduct() {
		Assertions.assertEquals(productRepository.getByIndex(0).getId(), 1);
	}

	@Test
	@DisplayName("Get products should return complete list of products")
	public void getProductsShouldReturnCompleteListOfProducts() {
		Assertions.assertEquals(productRepository.getProducts().count(), 100);
	}

	@Test
	@DisplayName("Search by SKU should return matching items")
	public void searchBySkuShouldReturnMatchingItems() {
		Assertions.assertTrue(productRepository.search("FC-").count() > 0);
	}

	@Test
	@DisplayName("Search by product name should return matching items")
	public void searchByProductNameShouldReturnMatchingItems() {
		Assertions.assertTrue(productRepository.search("cotton").count() > 0);
	}

	@Test
	@DisplayName("Get by product ID should return appropriate product")
	public void getByProductIdShouldReturnAppropriateProduct() {
		var expected = 1;
		var actual = productRepository
				.getByProductId(1)
				.getId();
		Assertions.assertEquals(expected, actual);
	}
}