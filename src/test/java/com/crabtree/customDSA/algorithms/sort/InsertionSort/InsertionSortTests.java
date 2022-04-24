package com.crabtree.customDSA.algorithms.sort.InsertionSort;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class InsertionSortTests {

	@Test
	@DisplayName("Sorts IntegersAscending")
	public void sortsIntegersAscending() {
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

}