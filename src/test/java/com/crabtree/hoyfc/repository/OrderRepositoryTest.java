package com.crabtree.hoyfc.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderRepositoryTest {

	private final OrderRepository orderRepository;

	OrderRepositoryTest(@Autowired OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Test
	@DisplayName("Get orders should return all orders")
	public void getOrdersShouldReturnAllOrders() {
		Assertions.assertEquals(105, orderRepository.getOrders().count());
	}

	@Test
	@DisplayName("Get order count should return count of all orders")
	public void getOrderCountShouldReturnCountOfAllOrders() {
		Assertions.assertEquals(105, orderRepository.getOrderCount());
	}

	@Test
	@DisplayName("Search by unique order ID should return appropriate order")
	public void searchByUniqueOrderIdShouldReturnAppropriateOrder() {
		var expected = "HOYFC-01101";
		var actual = orderRepository.search("HOYFC-01101").getByIndex(0).getPublicOrderId();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Search by order digits should return appropriate order")
	public void searchByOrderDigitsShouldReturnAppropriateOrder() {
		var expected = "HOYFC-01101";
		var actual = orderRepository.search("01101").getByIndex(0).getPublicOrderId();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Search by order status should return matching orders")
	public void searchByOrderStatusShouldReturnMatchingOrders() {
		Assertions.assertTrue(orderRepository.search("PENDING").count() > 0);
	}

	@Test
	@DisplayName("Search by customer name should return matching orders")
	public void searchByCustomerNameShouldReturnMatchingOrders() {
		Assertions.assertTrue(orderRepository.search("e").count() > 0);
	}
}