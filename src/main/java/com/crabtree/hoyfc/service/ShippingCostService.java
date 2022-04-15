package com.crabtree.hoyfc.service;

import com.crabtree.hoyfc.model.order.ShippingType;
import org.springframework.stereotype.Service;

@Service
public class ShippingCostService {

	public static Double getShippingCost(ShippingType shippingType) {
		switch (shippingType) {
			case FIRST_CLASS:
				return 2.50;
			case SECOND_CLASS:
				return 1.50;
			case EXPEDITED:
				return 3.50;
			case SATURDAY:
				return 4.50;
			default:
				throw new IllegalStateException("Unexpected value: " + shippingType);
		}
	}
}