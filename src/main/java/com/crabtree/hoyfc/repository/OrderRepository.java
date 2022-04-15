package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import org.springframework.stereotype.Service;

@Service
public class OrderRepository {

	private final DynamicArrayList<CustomerOrder> customerOrders;

	public OrderRepository() {
		this.customerOrders = new DynamicArrayList<>();
	}

	public static DynamicArrayList<CustomerOrder> search(String searchTerm) {
		return null;
	}

	public CustomerOrder save(CustomerOrder customerOrder) {
		return this.customerOrders.add(customerOrder);
	}

	public DynamicArrayList<CustomerOrder> getOrders() {
		return this.customerOrders;
	}

	public Integer getOrderCount() {
		return this.customerOrders.count();
	}
}