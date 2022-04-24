package com.crabtree.customDSA.algorithms.search.KMPSearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class KMPSearchTest {

	@Test
	@DisplayName("Should return one if substring present")
	public void shouldReturnOneIfSubstringPresent() {
		var haystack = "abc";
		var needle = "b";
		var expected = 1;
		var actual = KMPSearch.search(haystack, needle);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Should return negative one if substring not present")
	public void shouldReturnNegativeOneIfSubstringNotPresent() {
		var haystack = "abc";
		var expected = -1;
		var actual = KMPSearch.search(haystack, "x");
		Assertions.assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Should throw exception if haystack is empty")
	public void shouldThrowExceptionIfHaystackIsEmpty() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				KMPSearch.search("", "abc");
			}
		});
	}

	@Test
	@DisplayName("Should throw exception if haystack is null")
	public void shouldThrowExceptionIfHaystackIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				KMPSearch.search(null, "abc");
			}
		});
	}

	@Test
	@DisplayName("Should throw exception if needle is empty")
	public void shouldThrowExceptionIfNeedleIsEmpty() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				KMPSearch.search("abc", "");
			}
		});
	}

	@Test
	@DisplayName("Should throw exception if needle is null")
	public void shouldThrowExceptionIfNeedleIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				KMPSearch.search("abc", null);
			}
		});
	}
}