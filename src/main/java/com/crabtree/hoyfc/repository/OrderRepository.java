package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.algorithms.search.recursiveBinarySearch.RecursiveBinarySearch;
import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.model.customerOrder.comparatorFactory.OrderComparatorFactory;
import com.crabtree.hoyfc.service.pageSort.SortDirection;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderRepository {
	private final DynamicArrayList<CustomerOrder> customerOrders;
	private final InsertionSort insertionSort;

	public OrderRepository(InsertionSort insertionSort) {
		this.insertionSort = insertionSort;
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
			result.add(customerOrders.getByIndex(index));
			return result;
		}
	}

	public DynamicArrayList<CustomerOrder> search(String needle) {
		if (needle.startsWith("HOYFC")) {
			return searchByUniqueOrderId(needle);
		}

		if (StringUtils.isNumeric(needle)) {
			return searchByOrderIdDigits(needle);
		}

		if (searchTermIsOrderStatus(needle)) {
			return searchByOrderStatus(needle);
		}

		return searchByCustomerName(needle);
	}

	private DynamicArrayList<CustomerOrder> searchByOrderIdDigits(String needle) {
		var result = new DynamicArrayList<CustomerOrder>();
		for (CustomerOrder customerOrder : customerOrders) {
			var idHaystack = customerOrder
					.getPublicOrderId()
					.toLowerCase();

			if (KMPSearch.search(idHaystack, needle.toLowerCase()) == 1) {
				result.add(customerOrder);
			}
		}
		return result;
	}

	private DynamicArrayList<CustomerOrder> searchByCustomerName(String needle) {
		var result = new DynamicArrayList<CustomerOrder>();

		for (CustomerOrder customerOrder : customerOrders) {
			var nameHaystack = customerOrder
					.getOrderCustomer()
					.getCustomerName()
					.getFullName()
					.toLowerCase();

			if (KMPSearch.search(nameHaystack, needle.toLowerCase()) == 1) {
				result.add(customerOrder);
			}
		}
		return result;
	}

	private DynamicArrayList<CustomerOrder> searchByOrderStatus(String needle) {
		var result = new DynamicArrayList<CustomerOrder>();

		for (CustomerOrder customerOrder : customerOrders) {
			var nameHaystack = customerOrder
					.getOrderStatus()
					.toString()
					.toLowerCase();

			if (KMPSearch.search(nameHaystack, needle.toLowerCase()) == 1) {
				result.add(customerOrder);
			}
		}
		return result;
	}

	private boolean searchTermIsOrderStatus(String needle) {
		var statusValues = OrderStatus.values();
		for (int i = 0; i < statusValues.length; i++) {
			if (needle
					.toUpperCase()
					.equals(statusValues[i].toString())) {
				return true;
			}
		}
		return false;
	}

	private CustomerOrder getOrderByIndex(Integer index) {
		return this.customerOrders.getByIndex(index);
	}

	public CustomerOrder getOrderById(Integer orderId) {
		var bs = new RecursiveBinarySearch();
		return getOrderByIndex(bs.orderIdSearch(this.customerOrders, orderId));
	}

	public DequeImpl<CustomerOrder> getPendingCustomerOrdersByDateDescending() {
		insertionSort.sort(this.customerOrders, OrderComparatorFactory.getComparator("Status", SortDirection.ASC));
		var result = new DequeImpl<CustomerOrder>();
		for (int i = customerOrders.count() - 1; i >= 0; i--) {
			var order = getOrderById(i+1);
			if (order.getOrderStatus() == OrderStatus.PENDING) {
				result.addFirst(order);
			}
			else {
				break;
			}
		}
		return result;
	}

	public void setOrderStatus(CustomerOrder order, OrderStatus status) {
		var updatedOrder = getOrderById(order.getId());
		updatedOrder.setOrderStatus(status);
		this.customerOrders.put(order.getId() -1, updatedOrder);
	}

	public void sort(SortHelper sortingData) {
		var comparator = OrderComparatorFactory.getComparator(sortingData.getSortColumn(), sortingData.getSortDirection());
		insertionSort.sort(this.customerOrders, comparator);
	}
}