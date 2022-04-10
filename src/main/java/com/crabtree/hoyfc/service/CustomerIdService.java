package com.crabtree.hoyfc.service;

import org.springframework.stereotype.Service;

@Service
public class CustomerIdService {

	private int customerId = 0;

	public int getNext() {
		return ++customerId;
	}
}