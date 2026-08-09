package com.sklg.common;

import java.util.Map;
import java.util.TreeMap;

public class DuplicateWords {

	public static void main(String[] args) {
		findDuplicateWordsInGivenSentence(args);
	}

	private static void findDuplicateWordsInGivenSentence(String[] words) {
		if (words == null || words.length == 0) {
			System.out.println("Given sentence is empty or null");
			return;
		}

		System.out.println("Given input sentence i.e \"" + String.join(" ", words) + "\"");
		Map<String, Integer> wordCountMap = new TreeMap<>();
		for (String word : words) {
			if (wordCountMap.containsKey(word)) {
				wordCountMap.put(word, wordCountMap.get(word) + 1);
			} else {
				wordCountMap.put(word, 1);
			}
		}

		System.out.println("Output..!");
		for (String word : wordCountMap.keySet()) {
			if (wordCountMap.get(word) > 1) {
				System.out.println("Word \"" + word + "\" duplicate count - " + wordCountMap.get(word));
			}
		}
		System.out.println("Execution completed.");
	}
}
