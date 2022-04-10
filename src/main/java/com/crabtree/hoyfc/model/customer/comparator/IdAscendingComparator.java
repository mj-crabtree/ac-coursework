package com.crabtree.hoyfc.model.customer.comparator;

import com.crabtree.hoyfc.model.BaseEntity;

import java.util.Comparator;

public class IdAscendingComparator<T extends BaseEntity> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		return o1
				.getId()
				.compareTo(o2.getId());
	}
}