package com.crabtree.customDSA.dataStructures.hashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashMapTests {

	private static HashMap<String, Integer> data = new HashMap<>(HashMap.Type.OPEN_ADDRESSING, 1);
	@BeforeAll
	static void beforeAll() {
		data.put("A", 1);
	}

	@Test
	@DisplayName("Get should return value")
	public void getShouldReturnValue() {
		Integer expected = 1;
		assertEquals(expected, data.get("A"));
	}

	@Test
	@DisplayName("Remove should remove entry")
	public void removeShouldRemoveEntry() {
		Integer expected = 0;
		data.remove("A");
		var actual = data.size();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Contains should return true when value exists")
	public void containsShouldReturnTrueWhenValueExists() {
		var expected = true;
		var actual = data.contains("A");
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Contains should return false when value does not exist")
	public void containsShouldReturnFalseWhenValueDoesNotExist() {
		var expected = false;
		var actual = data.contains("B");
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Size with single element should return one")
	public void sizeWithSingleElementShouldReturnOne() {
		var expected = 1;
		var actual = data.size();
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Clear should remove all elements")
	public void clearShouldRemoveAllElements() {
		var expected = 0;
		data.clear();
		var actual = data.size();
		assertEquals(expected, actual);
	}
}