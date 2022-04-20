package com.crabtree.hoyfc.service.order;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.repository.OrderRepository;
import com.crabtree.hoyfc.service.pageSort.SortHelper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final InsertionSort insertionSort;

	public OrderService(OrderRepository orderRepository, InsertionSort insertionSort) {
		this.orderRepository = orderRepository;
		this.insertionSort = insertionSort;
	}

	public DynamicArrayList<CustomerOrder> getOrders() {
		return orderRepository.getOrders();
	}

	public CustomerOrder save(CustomerOrder order) {
		return orderRepository.save(order);
	}

	public Integer getOrderCount() {
		return orderRepository.getOrderCount();
	}

	public DynamicArrayList<CustomerOrder> search(String searchTerm) {
		return searchTerm.startsWith("HOYFC") || NumberUtils.isParsable(searchTerm) ? orderRepository.searchByUniqueOrderId(searchTerm) : orderRepository.search(searchTerm);
	}

	public CustomerOrder getOrderById(Integer orderId) {
		return orderRepository.getOrderById(orderId);
	}

	public DequeImpl<CustomerOrder> getPendingCustomerOrdersByDateDescending() {
		return orderRepository.getPendingCustomerOrdersByDateDescending();
	}

	public Integer getPendingOrderCount() {
		return orderRepository
				.getPendingCustomerOrdersByDateDescending()
				.size();
	}

	public void setOrderStatus(CustomerOrder order, OrderStatus status) {
		orderRepository.setOrderStatus(order, status);
	}

	public void sort(SortHelper sortingData) {
		this.orderRepository.sort(sortingData);
	}

	// public CustomerOrder binarySearchByOrderId(Integer orderId) {
	// 	var bs = Bin
	// }
}