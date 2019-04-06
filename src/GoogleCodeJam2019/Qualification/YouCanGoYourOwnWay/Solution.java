package GoogleCodeJam2019.Qualification.YouCanGoYourOwnWay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int numTests = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc

		for (int test = 1; test <= numTests; test++) {
			int length = in.nextInt();
			String path = in.next();
			printSolution(test, path);
		}
	}

	private static void printSolution(int test, String lydiasPath) {
		//literally just go the opposite of whatever lydia does lol
		StringBuilder myPath = new StringBuilder();
		for (char c : lydiasPath.toCharArray()) {
			if (c == 'S') {
				myPath.append('E');
			} else {
				myPath.append('S');
			}
		}
		System.out.println("Case #" + test + ": " + myPath);
	}
}
