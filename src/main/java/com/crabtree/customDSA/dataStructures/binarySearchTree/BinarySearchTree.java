package com.crabtree.customDSA.dataStructures.binarySearchTree;

public class BinarySearchTree<T extends Comparable<T>> {
	private static Object[] elements;
	private static int index;
	private int elementCount = 1;
	private T payload;
	private BinarySearchTree<T> leftChild;
	private BinarySearchTree<T> rightChild;

	public BinarySearchTree(T element) {
		payload = element;
		leftChild = rightChild = null;
	}

	public void add(T element) {
		if (element.compareTo(payload) < 0) {
			if (leftChild != null) {
				leftChild.add(element);
			}
			else {
				leftChild = new BinarySearchTree<>(element);
			}

		} else {
			if (rightChild != null) {
				rightChild.add(element);
			}
			else {
				rightChild = new BinarySearchTree<>(element);
			}

		}
		elementCount++;
	}

	public Object[] toArray() {
		elements = new Object[elementCount];
		index = 0;
		traverseInOrder();
		return elements;
	}

	private void traverseInOrder() {

		if (leftChild != null) {
			leftChild.traverseInOrder();
		}
		elements[index++] = payload;
		if (rightChild != null) {
			rightChild.traverseInOrder();
		}
	}

	public BinarySearchTree<T> removeElement(T element) {

		BinarySearchTree<T> root = this;

		if (element == null && root.payload == null) {
			root = null;
		}
		else if (element.compareTo(payload) < 0) {
			root.leftChild = root.leftChild.removeElement(element);
		}
		else if (element.compareTo(payload) > 0) {
			root.rightChild = root.rightChild.removeElement(element);
		}
		else {
			if (root.leftChild == null && root.rightChild == null) {
				root = null;
			}
			else if (root.leftChild == null) {
				root = root.rightChild;
			}
			else if (root.rightChild == null) {
				root = root.leftChild;
			}
			else {
				BinarySearchTree<T> temp = root.rightChild.getMinimumValue();
				root.payload = temp.payload;
				root.rightChild = root.rightChild.removeElement(temp.payload);
			}
		}
		elementCount--;
		return root;
	}

	public boolean contains(T element) {
		boolean b1 = false, b2 = false;
		if ((element == null && payload == null) || payload.equals(element))
			return true;
		else {
			if (leftChild != null)
				b1 = leftChild.contains(element);

			if (rightChild != null)
				b2 = rightChild.contains(element);

			return b1 || b2;
		}
	}

	public BinarySearchTree<T> getMinimumValue() {
		if (leftChild != null)
			return leftChild.getMinimumValue();
		else
			return this;
	}

}