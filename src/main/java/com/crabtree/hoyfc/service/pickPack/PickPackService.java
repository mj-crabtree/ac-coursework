package com.crabtree.hoyfc.service.pickPack;

import com.crabtree.customDSA.dataStructures.deque.DequeImpl;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.controller.pickpack.PickList;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
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

	public DynamicArrayList<PickListEntry> createPickList(List<Integer> orderIdCollection) {
		return pickPackRepository.getItemsToPick(orderIdCollection);
	}

	public DynamicArrayList<CustomerOrder> getPickedOrders(PickList pickList) {
		var result = new DynamicArrayList<CustomerOrder>();
		for (Integer orderId : pickList.getOrderIdCollection()) {
			result.add(orderService.getOrderById(orderId));
		}
		return result;
	}
}