package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderLineItem;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.model.pickListEntry.PickListEntry;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PickPackRepository {
	private final OrderService orderService;

	public PickPackRepository(OrderService orderService) {
		this.orderService = orderService;
	}

	public DequeImpl<CustomerOrder> getPendingCustomerOrders() {
		return this.getPendingCustomerOrdersByDateDescending();
	}

	private DequeImpl<CustomerOrder> getPendingCustomerOrdersByDateDescending() {
		var result = new DequeImpl<CustomerOrder>();

		for (int i = orderService
				.getOrders()
				.count() - 1; i >= 0; i--) {
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

	public DynamicArrayList<PickListEntry> getItemsToPick(List<Integer> orderIdCollection) {
		var map = new HashMap<Product, Integer>();

		for (Integer orderId : orderIdCollection) {
			var order = this.orderService.getOrderById(orderId);

			for (OrderLineItem lineItem : order.getLineItems()) {
				var lineItemProduct = lineItem.getProduct();
				var lineItemQuantity = lineItem.getCount();

				if (map.containsKey(lineItemProduct)) {
					map.put(lineItemProduct, map.get(lineItemProduct) + lineItemQuantity);
				}
				else {
					map.put(lineItemProduct, lineItemQuantity);
				}
			}
		}

		var result = new DynamicArrayList<PickListEntry>();
		for (Map.Entry<Product, Integer> entry : map.entrySet()) {
			result.add(new PickListEntry(entry.getKey(), entry.getValue()));
		}

		return result;
	}
}