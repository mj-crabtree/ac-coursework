package com.crabtree.customDSA.algorithms.search.KMPSearch;

public class KMPSearch {
	public static int search(String haystack, String needle) {
		return knuthMorrisPratt(haystack, needle);
	}

	private static int knuthMorrisPratt(String haystack, String needle) {
		if (haystack == null || haystack.isEmpty() || haystack.isBlank()) {
			throw new IllegalArgumentException("Haystack cannot be null or empty");
		}
		if (needle == null || needle.isEmpty() || needle.isBlank()) {
			throw new IllegalArgumentException("Needle cannot be null or empty");
		}


		int haystackLength = haystack.length();
		int needleLength = needle.length();

		int[] prefix = computePrefix(needle, needleLength);
		int caretPosition = 0;

		for (int i = 0; i < haystackLength; i++) {
			char haystackLetter = haystack.charAt(i);
			char needleLetter = needle.charAt(caretPosition);

			while (caretPosition > 0 && haystackLetter != needleLetter) {
				caretPosition = prefix[caretPosition - 1];
			}
			if (haystackLetter == needleLetter) {
				caretPosition++;
			}
			if (caretPosition == needleLength) {
				return 1;
			}
		}
		return -1;
	}

	private static int[] computePrefix(String needle, int needleLength) {
		int[] prefix = new int[needleLength];
		int caretPosition = 0;

		prefix[0] = 0;

		for (int i = 1; i < needleLength; i++) {
			char caretLetter = needle.charAt(caretPosition);
			char needleLetter = needle.charAt(i);

			while (caretPosition > 0 && caretLetter != needleLetter) {
				caretPosition = prefix[caretPosition - 1];
			}

			if (caretLetter == needleLetter) {
				caretPosition++;
			}

			prefix[i] = caretPosition;
		}
		return prefix;
	}
}