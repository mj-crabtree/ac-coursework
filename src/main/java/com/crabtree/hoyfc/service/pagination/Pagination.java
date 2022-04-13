package com.crabtree.hoyfc.service.pagination;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;

public class Pagination {

	// todo: delete this class && migrate usages to PaginationHelper.paginateCollection
	public static <T> DynamicDataStructure<T> paginateCollection(DynamicDataStructure<T> data, Integer pageNumber, Integer pageSize) {
		if (pageSize <= 0 || pageNumber <= 0) throw new IllegalArgumentException();

		int firstElement = (pageNumber - 1) * pageSize;

		if (data == null || data.count() <= firstElement) return new DynamicArrayList<T>();

		var out = data.subList(firstElement, Math.min(firstElement + pageSize, data.count()));

		System.out.println();

		return out;
	}

}