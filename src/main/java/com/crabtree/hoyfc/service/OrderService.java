package com.crabtree.hoyfc.service;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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
		return OrderRepository.search(searchTerm);
	}
}