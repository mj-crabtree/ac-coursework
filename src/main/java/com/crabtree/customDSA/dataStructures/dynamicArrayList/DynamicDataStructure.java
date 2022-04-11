package com.crabtree.customDSA.dataStructures.dynamicArrayList;

public interface DynamicDataStructure<T> {
	T add(T element);

	T remove(int index);

	void removeAll();

	T get(int index);

	boolean isOverloaded();

	boolean isUnderloaded();

	boolean isFull();

	boolean isEmpty();

	int count();

	void resizeUp();

	void resizeDown();

	void put(int index, T element);

	int newMaxCapacity(int newMaxCapacity);

	DynamicDataStructure<T> subList(Integer firstElement, Integer lastElement);
}