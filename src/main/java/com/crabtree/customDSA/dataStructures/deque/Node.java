package com.crabtree.customDSA.dataStructures.deque;

public class Node<S> {
	S payload;
	Node<S> next;
	Node<S> previous;

	public Node(S data) {
		this.payload = data;
	}
}