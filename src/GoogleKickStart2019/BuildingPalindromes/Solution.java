package GoogleKickStart2019.BuildingPalindromes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

// cd to leetcode\
// javac GoogleKickStart2019\BuildingPalindromes\Solution.java
// java GoogleKickStart2019.BuildingPalindromes.Solution < GoogleKickStart2019\BuildingPalindromes\input.txt
public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int numTests = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc


		for (int i = 1; i <= numTests; i++) {
			int count = 0;

			int numBlocks = in.nextInt(); //not needed
			int numQuestions = in.nextInt();
			String blocks = in.next();
			for (int q = 0; q < numQuestions; q++) {
				int start = in.nextInt() - 1;
				int end = in.nextInt() - 1;
				int[] chars = new int[26];
				for (int block = start; block <= end; block++) {
					chars[blocks.charAt(block)-'A']++;
				}
				boolean oddFound = false;
				boolean oddFoundTwice = false;
				for (int charCount : chars) {
					if (charCount % 2 == 1) {
						if (oddFound) {
							oddFoundTwice = true;
							break;
						}
						oddFound = true;
					}
				}
				if (!oddFoundTwice) count++;
			}

			System.out.println("Case #" + i + ": " + count);
		}
	}
}
