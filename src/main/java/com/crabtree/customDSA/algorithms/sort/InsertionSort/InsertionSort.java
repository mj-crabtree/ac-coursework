package com.crabtree.customDSA.algorithms.sort.InsertionSort;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class InsertionSort implements CustomSort {

	@Override
	public <T extends BaseEntity> void sort(DynamicDataStructure<T> collection, Comparator<? super T> comparator) {
		for (int i = 1; i < collection.count(); i++) {
			T firstElement = collection.getByIndex(i);
			int j = i;

			while (j > 0) {
				T secondElement = collection.getByIndex(j - 1);
				if (comparator.compare(firstElement, secondElement) >= 0) {
					break;
				}
				collection.put(j, secondElement);
				j--;
			}
			collection.put(j, firstElement);
		}
	}
}