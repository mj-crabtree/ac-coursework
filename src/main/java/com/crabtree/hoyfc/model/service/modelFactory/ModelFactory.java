package com.crabtree.hoyfc.model.service.modelFactory;

import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.order.CustomerOrder;
import com.crabtree.hoyfc.model.product.Product;

public class ModelFactory {
	public static Customer newCustomer() {
		return getCustomer();
	}

	public static Product newProduct() {
		return getProduct();
	}

	public static CustomerOrder newOrder() {
		return getOrder();
	}

	private static CustomerOrder getOrder() {
		return new CustomerOrder();
	}

	private static Product getProduct() {
		return new Product();
	}

	private static Customer getCustomer() {
		return new Customer();
	}
}