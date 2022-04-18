package com.crabtree.customDSA.dataStructures.hashSet;

import com.crabtree.customDSA.dataStructures.binarySearchTree.BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class HashSetImpl<T extends Comparable<T>> implements HashSet<T> {

	private final int initialSize = 128;
	private Object[] collection;
	private int size = 0;

	public HashSetImpl() {
		collection = new Object[initialSize];
	}

	private int hash(Object element) {

		if (element == null) return 0;

		int hashCode = element.hashCode();
		if (hashCode < 0) hashCode = -hashCode;
		return hashCode % initialSize;
	}

	@Override
	public boolean add(T element) {
		boolean added = true;
		int index = hash(element);

		if (collection[index] == null) {
			collection[index] = new BinarySearchTree<>(element);
		} else if (((BinarySearchTree<T>) collection[index]).contains(element)) {
			added = false;
		} else {
			((BinarySearchTree<T>) collection[index]).add(element);
		}

		if (added) size++;
		return added;
	}

	@Override
	public boolean addAll(HashSet<? extends T> collection) {
		boolean setChanged = false;
		Object[] arr = collection.toArray();
		for (Object o : arr) {
			setChanged |= this.add((T) o);
		}
		return setChanged;
	}

	@Override
	public boolean containsAll(HashSet<?> collection) {
		Object[] arr = collection.toArray();
		for (Object o : arr) {
			if (!this.contains((T) o)) return false;
		}
		return true;
	}

	@Override
	public boolean removeAll(HashSet<?> collection) {
		Object[] arr = collection.toArray();
		boolean setChanged = false;
		for (Object o : arr) {
			setChanged |= this.remove((T) o);
		}
		return setChanged;
	}

	@Override
	public boolean contains(Object element) {

		int index = hash(element);
		if (collection[index] != null && ((BinarySearchTree<T>) collection[index]).contains((T) element)) return true;
		else return false;
	}

	@Override
	public boolean remove(Object element) {
		int index = hash(element);
		if (collection[index] != null && ((BinarySearchTree<T>) collection[index]).contains((T) element)) {
			collection[index] = ((BinarySearchTree<T>) collection[index]).removeElement((T) element);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		Object[] bstArr;
		int index = 0;
		for (Object o : collection) {
			if (o != null) {
				bstArr = ((BinarySearchTree<T>) o).toArray();
				for (Object e : bstArr)
					arr[index++] = e;
			}
		}
		return arr;
	}

	@Override
	public void clear() {
		collection = new Object[initialSize];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public List<T> toList() {
		// helper method to return a more functional generic List<T>

		var objectArray = toArray();
		var result = new ArrayList<T>();

		for (int i = 0; i < objectArray.length; i++) {
			result.add((T) objectArray[i]);
		}
		return result;
	}

	@Override
	public String toString() {
		Object[] arr = toArray();
		String str = "";
		int i;
		for (i = 0; i < arr.length - 1; i++)
			str += ((T) arr[i]) + ", ";
		str += ((T) arr[i]).toString();
		return str;
	}
}