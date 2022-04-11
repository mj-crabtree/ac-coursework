package com.crabtree.customDSA.dataStructures.dynamicArrayList;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;

import java.util.Arrays;
import java.util.Comparator;

public abstract class AbstractDynamicDataStructure<T> implements DynamicDataStructure<T> {

	protected final int INITIAL_CAPACITY = 16;
	protected Object[] collection;
	protected int currentCapacity;
	protected int maxCapacity;

	public AbstractDynamicDataStructure() {
		this.currentCapacity = 0;
	}

	public T get(int index) {
		return (T) this.collection[index];
	}

	public boolean isOverloaded() {
		return this.currentCapacity == this.maxCapacity;
	}

	public boolean isUnderloaded() {
		return this.maxCapacity > INITIAL_CAPACITY && this.currentCapacity * 4 <= this.maxCapacity;
	}

	public boolean isFull() {
		return this.currentCapacity == this.maxCapacity;
	}

	public boolean isEmpty() {
		return this.currentCapacity == 0;
	}

	public int count() {
		return this.currentCapacity;
	}

	public void resizeUp() {
		this.collection = Arrays.copyOf(this.collection, newMaxCapacity(this.maxCapacity * 2));
	}

	public void resizeDown() {
		this.collection = Arrays.copyOf(this.collection, newMaxCapacity(this.maxCapacity / 2));
	}

	public int newMaxCapacity(int newMaxCapacity) {
		this.maxCapacity = newMaxCapacity;
		return this.maxCapacity;
	}
}