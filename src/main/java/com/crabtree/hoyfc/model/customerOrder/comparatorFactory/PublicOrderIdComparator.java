package com.crabtree.hoyfc.model.customerOrder.comparatorFactory;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;

import java.util.Comparator;

public class PublicOrderIdComparator implements Comparator<CustomerOrder> {
	@Override
	public int compare(CustomerOrder o1, CustomerOrder o2) {
		return o1
				.getPublicOrderId()
				.compareTo(o2.getPublicOrderId());
	}
}