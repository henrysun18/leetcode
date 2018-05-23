package LongestPalindromicSubstring;

/**
 * Created by Henry on 5/20/2018.
 * Problem 5
 */
public class Solution {

	public static String longestPalindrome(String s) {
		int len = s.length();
		int maxPalindroneLength = 0;
		String longest = "";

		for (int i = len; i > 0; i--) {
			// try all substrings with length = i
			for (int k = 0; k <= len - i; k++) {
				String testSubstring = s.substring(k, k + i);
				int testSubstringLength = testSubstring.length();
				if (isPalindrome(testSubstring) && testSubstringLength > maxPalindroneLength) {
					longest = testSubstring;
					maxPalindroneLength = testSubstringLength;
				}
			}
		}
		return longest;
	}

	private static boolean isPalindrome(String s) {
		int len = s.length();
		for (int i = 0; i < len / 2; i++) {
			if (s.charAt(i) != s.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main (String[] args) {
		longestPalindrome("babds");
	}
}
