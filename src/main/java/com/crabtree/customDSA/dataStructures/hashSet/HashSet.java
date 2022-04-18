package com.crabtree.customDSA.dataStructures.hashSet;

public interface HashSet<T> {
	boolean add(T element);

	boolean addAll(HashSet<? extends T> collection);

	boolean containsAll(HashSet<?> collection);

	boolean removeAll(HashSet<?> collection);

	boolean contains(Object object);

	boolean remove(Object object);

	Object[] toArray();

	void clear();

	int hashCode();

	boolean equals(Object object);

	boolean isEmpty();

	int size();
}