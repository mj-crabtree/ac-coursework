package com.crabtree.customDSA.dataStructures.dynamicArrayList;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;

import java.util.Comparator;
import java.util.Iterator;

public class DynamicArrayList<T> extends AbstractDynamicDataStructure<T> implements Iterable<T> {

	public DynamicArrayList() {
		super();
		this.collection = new Object[super.INITIAL_CAPACITY];
		this.maxCapacity = super.INITIAL_CAPACITY;
	}

	public T add(T element) {
		if (element == null) {
			throw new IllegalStateException();
		}
		if (isOverloaded()) {
			resizeUp();
		}

		T result = null;
		this.collection[this.currentCapacity] = result = element;
		this.currentCapacity++;

		return result;
	}

	public T remove(int index) {
		T removedElement = get(index);
		removeItem(this.collection, index);

		if (isUnderloaded()) {
			resizeDown();
		}

		return removedElement;
	}

	public void removeAll() {
		for (int i = count(); i >= 0; i--) {
			collection[i] = null;
		}
		initialise();
	}

	public void put(int index, T element) {
		this.collection[index] = element;
	}

	public void sort(Comparator<?> comparator) {

	}

	@Override
	public DynamicArrayList<T> subList(Integer firstElement, Integer lastElement) {
		DynamicArrayList<T> output = new DynamicArrayList<>();
		for (int i = firstElement; i < lastElement; i++) {
			if (this.get(i) != null) {
				output.add(this.get(i));
			}
			else {
				return output;
			}
		}
		return output;
	}

	private void initialise() {
		this.collection = new Object[INITIAL_CAPACITY];
		this.currentCapacity = 0;
		this.maxCapacity = INITIAL_CAPACITY;
	}

	private void removeItem(Object[] collection, int index) {
		int newCurrentCapacity = this.currentCapacity - 1;

		if (newCurrentCapacity > index) {
			System.arraycopy(collection, index + 1, collection, index, newCurrentCapacity - index);
		}

		collection[this.currentCapacity = newCurrentCapacity] = null;
	}

	@Override
	public Iterator<T> iterator() {
		return new CustomArrayListIterator();
	}

	private class CustomArrayListIterator implements Iterator<T> {

		private int position;

		@Override
		public boolean hasNext() {
			return this.position != currentCapacity;
		}

		@Override
		public T next() {
			T element = DynamicArrayList.this.get(this.position);
			this.position++;
			return element;
		}
	}
}