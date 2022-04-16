package com.crabtree.hoyfc.service.order;

import org.springframework.stereotype.Service;

@Service
public class OrderIdService {
	private static Integer orderId = 1000;
	private static final String prefix = "HOYFC-";

	public static String getNextOrderId() {
		return prefix +
				String.format("%05d", ++orderId);
	}
}