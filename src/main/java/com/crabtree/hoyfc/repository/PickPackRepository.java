package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.service.order.OrderService;
import org.springframework.stereotype.Service;

@Service
public class PickPackRepository {
	private OrderService orderService;

	public PickPackRepository(OrderService orderService) {
		this.orderService = orderService;
	}
	public DequeImpl<CustomerOrder> getPendingCustomerOrders() {
		return this.getPendingCustomerOrdersByDateDescending();
	}
	private DequeImpl<CustomerOrder> getPendingCustomerOrdersByDateDescending() {
		var result = new DequeImpl<CustomerOrder>();

		for (int i = orderService.getOrders().count() - 1; i >= 0; i--) {
			var order = orderService.getOrderById(i);
			if (order.getOrderStatus() == OrderStatus.PENDING) {
				result.addFirst(order);
			}
			else {
				break;
			}
		}
		return result;
	}
}