package com.crabtree.hoyfc.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerRepositoryTest {

	private final CustomerRepository customerRepository;

	CustomerRepositoryTest(@Autowired CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Test
	@DisplayName("Size on boot should return one hundred customers")
	public void sizeOnBootShouldReturnOneHundredCustomers() {
		var expected = 100;
		var actual = customerRepository.count();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Get customer by id should return correct customer id")
	public void getCustomerByIdShouldReturnCorrectCustomerId() {
		var expected = 1;
		var actual = customerRepository.getCustomerById(1).getId();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Get customer by index should return correct customer")
	public void getCustomerByIndexShouldReturnCorrectCustomer() {
		var expected = 1;
		var actual = customerRepository
				.getCustomerByIndex(0)
				.getId();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Get customers should return all customers")
	public void getCustomersShouldReturnAllCustomers() {
		var expected = 100;
		var actual = customerRepository
				.getCustomers()
				.count();
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Customer name search should return matching results")
	public void customerNameSearchShouldReturnMatchingResults() {
		var actual = customerRepository.search("e");
		Assertions.assertTrue(actual.count() > 0);
	}

	@Test
	@DisplayName("Customer email search should return matching results")
	public void customerEmailSearchShouldReturnMatchingResults() {
		var actual = customerRepository.search("gmail");
		Assertions.assertTrue(actual.count() > 0);
	}
}