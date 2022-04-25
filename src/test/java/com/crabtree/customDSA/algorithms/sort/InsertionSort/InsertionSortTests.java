package com.crabtree.customDSA.algorithms.sort.InsertionSort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class InsertionSortTests {

	@Test
	@DisplayName("Should sort integers ascending")
	public void shouldSortIntegersAscending() {
		var expected = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			expected.add(i);
		}

		List<Integer> actual = new ArrayList<>();
		for (int i = 9; i >= 0; i--) {
			actual.add(i);
		}
		Comparator<Integer> c = (o1, o2) -> o1.compareTo(o2);
		var is = new InsertionSort();
		is.testSort(actual, c);

		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Should sort integers descending")
	public void shouldSortIntegersDescending() {
		var expected = new ArrayList<Integer>();
		for (int i = 9; i >= 0; i--) {
			expected.add(i);
		}

		List<Integer> actual = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			actual.add(i);
		}
		Comparator<Integer> c = (o1, o2) -> o2.compareTo(o1);
		var is = new InsertionSort();
		is.testSort(actual, c);

		Assertions.assertEquals(expected, actual);
	}

}