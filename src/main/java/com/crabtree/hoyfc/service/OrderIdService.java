package com.crabtree.hoyfc.service;

import org.springframework.stereotype.Service;

@Service
public class OrderIdService {
	private static Integer orderId = 1000;
	private static String prefix = "HOYFC-";

	public static String getNextOrderId() {
		return prefix +
				String.format("%05d", ++orderId);
	}
}