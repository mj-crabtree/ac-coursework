package com.crabtree.customDSA.dataStructures.dynamicArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicArrayListTest {

	private DynamicArrayList<Integer> collection = new DynamicArrayList<Integer>();

	@Test
	@DisplayName("Add should add element to empty collection in zeroth index")
	public void addShouldAddElementToEmptyCollectionInZerothIndex() {
		collection.add(1);
		Assertions.assertEquals(1, collection.getByIndex(0));
	}

	@Test
	@DisplayName("Adding one hundred items should resize collection up")
	public void addingOneHundredItemsShouldResizeCollectionUp() {
		for (int i = 0; i < 32; i++) {
			collection.add(i);
		}
		assertTrue(collection.maxCapacity > 16);
	}

	@Test
	@DisplayName("Removing 100 items should resize collection down")
	public void removing100ItemsShouldResizeCollectionDown() {
		for (int i = 0; i < 200; i++) {
			collection.add(i);
		}
		assertTrue(collection.maxCapacity > 16);

		for (int i = 200; i >= 16; i--) {
			collection.remove(i);
		}
		assertTrue(collection.maxCapacity < 100);
	}
}