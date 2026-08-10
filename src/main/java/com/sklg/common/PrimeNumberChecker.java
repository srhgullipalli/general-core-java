package com.sklg.common;

public class PrimeNumberChecker {

	public static void main(String[] args) {
		if (args == null || args.length == 0) {
			System.out.println("Please provide number(s) as augument to check prime..!");
		}

		for (String arg : args) {
			try {
				Integer integerInput = Integer.parseInt(arg);
				System.out.println(arg + " is " + (isPrime(integerInput) ? "" : "not ") + "a prime number");
			} catch (NumberFormatException nfe) {
				System.out.println("\"" + arg + "\" is not a number, ignored the prime number check..!");
			}
		}
	}

	private static boolean isPrime(Integer input) {
		if (input == 0 || input == 1) {
			return false;
		}
		if (input == 2) {
			return true;
		}
		for (int i = 2; i < (input/2); i++) {
			if (input % i == 0) {
				return false;
			}
		}
		return true;
	}
}
