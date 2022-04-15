package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;

import java.util.Comparator;

public class PublicOrderIdComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		var key = (String) o1;
		var comparison = (CustomerOrder) o2;

		return key
				.toLowerCase()
				.compareTo(comparison
						.getPublicOrderId()
						.toLowerCase());
	}
}