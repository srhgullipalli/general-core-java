package com.sklg.common;

import java.util.Map;
import java.util.TreeMap;

public class DuplicateCharacters {
	public static void main (String[] args) {
		findAndPrintDuplateCharacters(String.join("", args));
	}

	public static void findAndPrintDuplateCharacters(String input) {
		if (input == null || input.isEmpty()) {
			System.out.println("String is empty or null");
			return;
		}

		System.out.println("Input: " +input);
		Map<Character, Integer> charCountMap = new TreeMap<>();
		for (Character ch : input.toCharArray()) {
			if (charCountMap.containsKey(ch)) {
				charCountMap.put(ch, charCountMap.get(ch) + 1);
			} else {
				charCountMap.put(ch, 1);
			}
		}

		System.out.println("Output...");
		for (Character ch : charCountMap.keySet()) {
			if (charCountMap.get(ch) > 1) {
				System.out.println("Character '" + ch + "' duplicate count - " + charCountMap.get(ch));
			}
		}
		System.out.println("Execution complated.");
	}
}
