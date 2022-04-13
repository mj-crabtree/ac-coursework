package com.crabtree.hoyfc.service;

import org.springframework.stereotype.Service;

@Service
public class ProductIdService {

	private static int productId = 0;

	public static int getNext() {
		return ++productId;
	}
}