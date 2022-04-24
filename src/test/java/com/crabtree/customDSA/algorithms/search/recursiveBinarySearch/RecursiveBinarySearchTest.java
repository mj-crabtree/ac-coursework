package com.crabtree.customDSA.algorithms.search.recursiveBinarySearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecursiveBinarySearchTest {

	private final int[] data = new int[]{1, 2, 3, 4, 5};
	private final RecursiveBinarySearch recursiveBinarySearch = new RecursiveBinarySearch();

	@Test
	@DisplayName("Should return item index if item is found")
	public void shouldReturnItemIndexIfItemIsFound() {
		var expected = 0;
		var actual = recursiveBinarySearch.testSearch(this.data, 1);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Should return -1 if item is not found")
	public void shouldReturn1IfItemIsNotFound() {
		var expected = -1;
		var actual = recursiveBinarySearch.testSearch(this.data, -1);
		Assertions.assertEquals(expected, actual);
	}
}