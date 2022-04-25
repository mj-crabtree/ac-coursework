package com.crabtree.customDSA.dataStructures.deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequeImplTest {

	@Test
	@DisplayName("Add first should make new item head")
	public void addFirstShouldMakeNewItemHead() {
		var deque = new DequeImpl<Integer>();
		deque.addFirst(1);
		Assertions.assertEquals(1, deque.peekFirst());
	}

	@Test
	@DisplayName("Add Last should make new item tail")
	public void addLastShouldMakeNewItemTail() {
		var deque = new DequeImpl<Integer>();
		for (int i = 1; i < 6; i++) {
			deque.addLast(i);
		}
		Assertions.assertEquals(5, deque.peekLast());
	}

	@Test
	@DisplayName("Pop first should return value at head")
	public void popFirstShouldReturnValueAtHead() {
		var expected = 1;
		var deque = new DequeImpl<Integer>();
		deque.addFirst(1);
		assertEquals(expected, deque.popFirst());
	}

	@Test
	@DisplayName("Pop last should return value at tail")
	public void popLastShouldReturnValueAtTail() {
		var deque = new DequeImpl<Integer>();
		for (int i = 1; i < 6; i++) {
			deque.addLast(i);
		}
		Assertions.assertEquals(5, deque.popLast());
	}

	@Test
	@DisplayName("Size with one element should return one")
	public void sizeWithOneElementShouldReturnOne() {
		var deque = new DequeImpl<Integer>();
		deque.addFirst(1);
		Assertions.assertEquals(1, deque.size());
	}

	@Test
	@DisplayName("Empty should return true with empty deque")
	public void emptyShouldReturnTrueWithEmptyDeque() {
		var deque = new DequeImpl<Integer>();
		Assertions.assertTrue(deque.isEmpty());
	}

	@Test
	@DisplayName("Empty should return false with non empty queue")
	public void emptyShouldReturnFalseWithNonEmptyQueue() {
		var deque = new DequeImpl<Integer>();
		deque.addFirst(1);
		Assertions.assertFalse(deque.isEmpty());
	}
}