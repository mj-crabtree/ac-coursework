package com.crabtree.customDSA.dataStructures.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeImpl<T> implements Deque<T>, Iterable<T> {

	Node<T> head = null;
	Node<T> tail = null;
	int size = 0;

	@Override
	public void addFirst(T element) {
		var newNode = new Node<T>(element);
		if (this.head == null) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			newNode.next = this.head;
			this.head.previous = newNode;
			this.head = newNode;
		}
		this.size++;
	}

	@Override
	public void addLast(T element) {
		var newNode = new Node<T>(element);
		if (this.tail == null) {
			this.head = newNode;
			this.tail = newNode;
		}
		else {
			newNode.previous = this.tail;
			this.tail.next = newNode;
			this.tail = newNode;
		}
		this.size++;
	}

	@Override
	public T popFirst() {
		if (head == null) {
			throw new NoSuchElementException("Deque is empty");
		}

		T result = this.head.payload;

		if (this.head == this.tail) {
			this.head = null;
			this.tail = null;
		}
		else {
			this.head.next.previous = null;
			Node<T> oldHead = this.head;
			this.head = this.head.next;
			oldHead.next = null;
		}
		this.size--;
		return result;
	}

	@Override
	public T popLast() {
		if (tail == null) {
			throw new NoSuchElementException("Deque is empty");
		}

		T result = this.tail.payload;

		if (head == tail) {
			this.head = null;
			this.tail = null;
		}
		else {
			this.tail.next = null;
			Node<T> oldTail = this.tail;
			this.tail = this.tail.previous;
			oldTail.previous = null;
		}

		size--;
		return result;
	}

	@Override
	public T peekFirst() {
		return this.head.payload;
	}

	@Override
	public T peekLast() {
		return this.tail.payload;
	}

	@Override
	public Integer size() {
		return this.size;
	}

	@Override
	public Boolean isEmpty() {
		return this.head == null;
	}

	@Override
	public Iterator<T> iterator() {
		return new DequeIterator<T>();
	}

	private class DequeIterator<I> implements Iterator<T> {
		private Node<T> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (hasNext()) {
				T payload = this.current.payload;
				this.current = this.current.next;
				return payload;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}
}