package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.customer.comparatorFactory.CustomerComparatorFactory;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.comparatorFactory.OrderComparatorFactory;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.model.product.comparatorFactory.ProductComparatorFactory;
import com.crabtree.hoyfc.service.pageSort.SortDirection;

public class RecursiveBinarySearch {

	public Integer testSearch(int[] data, Integer key) {
		return testSearch(data, key, 0, data.length);
	}

	public int productIdSearch(DynamicDataStructure<Product> collection, Integer key) {
		var is = new InsertionSort();
		is.sort(collection, ProductComparatorFactory.getComparator("Id", SortDirection.ASC));
		return searchForProductId(collection, key, 0, collection.count());
	}

	private int searchForProductId(DynamicDataStructure<Product> collection, Integer key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = key.compareTo(collection
				.getByIndex(median)
				.getId());

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return searchForProductId(collection, key, left, median);
		}
		else {
			return searchForProductId(collection, key, median + 1, right);
		}
	}

	public int orderIdSearch(DynamicDataStructure<CustomerOrder> collection, Integer key) {
		var is = new InsertionSort();
		is.sort(collection, OrderComparatorFactory.getComparator("InternalId", SortDirection.ASC));
		return searchForOrderId(collection, key, 0, collection.count());
	}

	public int customerIdSearch(DynamicArrayList<Customer> collection, Integer key) {
		var is = new InsertionSort();
		is.sort(collection, CustomerComparatorFactory.getComparator("Id", SortDirection.ASC));
		return searchForCustomerId(collection, key, 0, collection.count());
	}

	public <T extends CustomerOrder> int search(DynamicDataStructure<T> collection, String key) {

		var is = new InsertionSort();
		is.sort(collection, OrderComparatorFactory.getComparator("Id", SortDirection.ASC));

		return recursiveBinarySearch(collection, key, 0, collection.count());
	}

	private <T extends CustomerOrder> int recursiveBinarySearch(DynamicDataStructure<T> collection, String key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = key.compareTo(collection
				.getByIndex(median)
				.getPublicOrderId());

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

	private int searchForOrderId(DynamicDataStructure<CustomerOrder> collection, Integer key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = key.compareTo(collection
				.getByIndex(median)
				.getId());

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return searchForOrderId(collection, key, left, median);
		}
		else {
			return searchForOrderId(collection, key, median + 1, right);
		}
	}

	private int searchForCustomerId(DynamicDataStructure<Customer> collection, Integer key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = key.compareTo(collection
				.getByIndex(median)
				.getId());

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return searchForCustomerId(collection, key, left, median);
		}
		else {
			return searchForCustomerId(collection, key, median + 1, right);
		}
	}

	private Integer testSearch(int[] data, Integer key, int left, int right) {
		if (right <= left) return -1;

		int median = (left + right) >>> 1;
		int comparison = Integer.compare(key, data[median]);

		if (comparison == 0) {
			return median;
		}
		else if (comparison < 0) {
			return testSearch(data, key, left, median);
		}
		else {
			return testSearch(data, key, median + 1, right);
		}
	}
}