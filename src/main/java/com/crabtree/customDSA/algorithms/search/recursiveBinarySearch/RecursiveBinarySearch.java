package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;

import java.util.Comparator;

public class RecursiveBinarySearch {

	public <T extends BaseEntity> int search(DynamicDataStructure<T> collection, String key) {

		Comparator comparator;

		if (key.startsWith("HOYFC")) {
			comparator = new PublicOrderIdComparator();
		}
		else {
			comparator = new OrderCustomerNameComparator();
		}

		var is = new InsertionSort();
		is.sort(collection, comparator);

		return recursiveBinarySearch(collection, key, 0, collection.count(), comparator);
	}

	private <T, K> int recursiveBinarySearch(DynamicDataStructure<T> collection, K key, int left, int right, Comparator comparator) {
		if (right < left) return -1;

		int median = (left + right) >>> 1;
		int comparison = comparator.compare(key, collection.get(median));

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return recursiveBinarySearch(collection, key, left, median, comparator);
		}
		else {
			return recursiveBinarySearch(collection, key, median + 1, right, comparator);
		}
	}
}