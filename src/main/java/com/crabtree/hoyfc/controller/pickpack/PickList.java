package com.crabtree.hoyfc.controller.pickpack;

import java.util.ArrayList;
import java.util.List;

public class PickList {
	private List<Integer> orderIdCollection;

	public PickList() {
		this.orderIdCollection = new ArrayList<>();
	}

	public List<Integer> getOrderIdCollection() {
		return orderIdCollection;
	}

	public void setOrderIdCollection(List<Integer> orderIdCollection) {
		this.orderIdCollection = orderIdCollection;
	}
}