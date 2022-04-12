package com.crabtree.customDSA.algorithms.sort.InsertionSort;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;

import java.util.Comparator;

public interface CustomSort {

	<T extends BaseEntity> void sort(DynamicDataStructure<T> collection, Comparator<? super T> comparator);
}