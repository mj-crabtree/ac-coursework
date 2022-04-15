package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;

import java.util.Comparator;

public class OrderCustomerNameComparator implements Comparator<Object> {

	@Override
	public int compare(Object o1, Object o2) {
		var key = (String) o1;
		var comparison = (CustomerOrder) o2;

		return key
				.toUpperCase()
				.compareTo(comparison
						.getOrderCustomer()
						.getCustomerName()
						.getFullName()
						.toLowerCase());
	}
}