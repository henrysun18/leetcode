package LongestPalindromicSubstring;

/**
 * Created by Henry on 5/20/2018.
 * Problem 5
 */
public class Solution {

	static String str;
	static int strLength;
	static String longestPalindrome;
	static int maxPalindromeLength;

	public static String longestPalindrome(String s) {
		str = s;
		strLength = s.length();
		longestPalindrome = s.substring(0,1);
		maxPalindromeLength = 1;

		for (int i = 1; i < strLength; i++) {
			getLongestOddLengthPalindromeCenteredAtIndex(i);
		}

		for (int i = 0; i < strLength -1; i++) {
			getLongestEvenLengthPalindromeLeftIndexStartingAt(i);
		}
		return longestPalindrome;
	}

	private static void getLongestOddLengthPalindromeCenteredAtIndex(int centerIndex) {
		// start with substr of length 3
		int leftIndex = centerIndex - 1;
		int rightIndex = centerIndex + 1;

		checkPalindromes(leftIndex, rightIndex);
	}

	private static void getLongestEvenLengthPalindromeLeftIndexStartingAt(int leftIndex) {
		// start with substr of length 2; maxPalindrome length at this point is guaranteed to be odd#
		int rightIndex = leftIndex + 1;

		checkPalindromes(leftIndex, rightIndex);
	}

	private static void checkPalindromes(int leftIndex, int rightIndex) {
		int substringLength = rightIndex - leftIndex + 1;
		while (leftIndex >= 0 && rightIndex < strLength) {
			if (str.charAt(leftIndex) == str.charAt(rightIndex)) {
				if (substringLength > maxPalindromeLength) {
					maxPalindromeLength = substringLength;
					longestPalindrome = str.substring(leftIndex, rightIndex+1);
				}
				leftIndex--;
				rightIndex++;
				substringLength += 2;
			} else {
				break;
			}
		}
	}

	public static void main (String[] args) {
		System.out.println(longestPalindrome("aaabaaaa"));
	}
}
