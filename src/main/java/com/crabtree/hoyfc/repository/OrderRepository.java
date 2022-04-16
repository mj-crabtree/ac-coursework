package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.algorithms.search.recursiveBinarySearch.RecursiveBinarySearch;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import org.springframework.stereotype.Service;

@Service
public class OrderRepository {

	private final DynamicArrayList<CustomerOrder> customerOrders;

	public OrderRepository() {
		this.customerOrders = new DynamicArrayList<>();
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

	public DynamicArrayList<CustomerOrder> searchByUniqueOrderId(String searchTerm) {
		var result = new DynamicArrayList<CustomerOrder>();
		var rbs = new RecursiveBinarySearch();
		var index = rbs.search(this.customerOrders, searchTerm);
		if (index == -1) {
			return result;
		}
		else {
			result.add(customerOrders.get(index));
			return result;
		}
	}

	public DynamicArrayList<CustomerOrder> search(String needle) {
		var result = new DynamicArrayList<CustomerOrder>();

		for (CustomerOrder customerOrder : customerOrders) {
			var nameHaystack = customerOrder
					.getOrderCustomer()
					.getCustomerName()
					.getFullName()
					.toLowerCase();

			if (KMPSearch.search(nameHaystack, needle) == 1) {
				result.add(customerOrder);
			}
		}
		return result;
	}

	public CustomerOrder getOrderById(Integer orderId) {
		return this.customerOrders.get(orderId - 1);
	}
}