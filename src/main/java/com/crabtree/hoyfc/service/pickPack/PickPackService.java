package com.crabtree.hoyfc.service.pickPack;

import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.customDSA.dataStructures.hashSet.HashSetImpl;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderLineItem;
import com.crabtree.hoyfc.model.pickListEntry.PickListEntry;
import com.crabtree.hoyfc.repository.PickPackRepository;
import com.crabtree.hoyfc.service.order.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PickPackService {
	private final PickPackRepository pickPackRepository;
	private final OrderService orderService;

	public PickPackService(PickPackRepository pickPackRepository, OrderService orderService) {
		this.pickPackRepository = pickPackRepository;
		this.orderService = orderService;
	}

	public DequeImpl<CustomerOrder> getPendingCustomerOrders() {
		return this.pickPackRepository.getPendingCustomerOrders();
	}

	public List<PickListEntry> createPickList(List<Integer> orderIdCollection) {
		var pickListEntries = new HashSetImpl<PickListEntry>();
		var pendingOrders = new DynamicArrayList<CustomerOrder>();

		for (Integer orderId : orderIdCollection) {
			var pendingOrder = orderService.getOrderById(orderId);
			pendingOrders.add(pendingOrder);
		}

		for (CustomerOrder pendingOrder : pendingOrders) {
			var lineItems = pendingOrder.getLineItems();
			for (OrderLineItem lineItem : lineItems) {
				var entry = new PickListEntry(lineItem.getProduct(), lineItem.getCount());
				pickListEntries.add(entry);
			}
		}
		return pickListEntries.toList();
	}
}