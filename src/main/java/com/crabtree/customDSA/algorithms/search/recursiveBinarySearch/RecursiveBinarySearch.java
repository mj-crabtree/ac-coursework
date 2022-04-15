package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.comparatorFactory.PublicOrderIdComparator;

import java.util.Comparator;

public class RecursiveBinarySearch {

	public <T extends CustomerOrder> int search(DynamicDataStructure<T> collection, String key) {

		var is = new InsertionSort();
		is.sort(collection, new PublicOrderIdComparator());

		return recursiveBinarySearch(collection, key, 0, collection.count());
	}

	private <T extends CustomerOrder> int recursiveBinarySearch(DynamicDataStructure<T> collection, String key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = key.compareTo(collection.get(median).getPublicOrderId());

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return recursiveBinarySearch(collection, key, left, median);
		}
		else {
			return recursiveBinarySearch(collection, key, median + 1, right);
		}
	}
}