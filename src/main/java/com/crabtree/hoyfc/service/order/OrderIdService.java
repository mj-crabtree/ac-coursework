package com.crabtree.hoyfc.service.order;

import org.springframework.stereotype.Service;

@Service
public class OrderIdService {
	private static final String prefix = "HOYFC-";
	private static Integer orderId = 1000;

	public static String getNextOrderId() {
		return prefix +
				String.format("%05d", ++orderId);
	}
}