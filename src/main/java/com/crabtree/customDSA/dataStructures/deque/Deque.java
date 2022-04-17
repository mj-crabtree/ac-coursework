package com.crabtree.customDSA.dataStructures.deque;

import java.util.NoSuchElementException;

public interface Deque<T> {
	void addFirst(T element);

	void addLast(T element);

	T popFirst() throws NoSuchElementException;

	T popLast() throws NoSuchElementException;

	T peekFirst();

	T peekLast();

	Integer size();

	Boolean isEmpty();
}