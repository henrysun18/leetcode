package LongestSubstringWithoutRepeatingCharacters;

/**
 * Created by Henry on 5/17/2018.
 * Problem 3
 */
public class Solution {
	public static int lengthOfLongestSubstring(String s) {
		StringBuilder sb = new StringBuilder();
		int longestLength = 0;

		for (int i = 0; i < s.length(); i++) {
			String c = s.substring(i, i+1);
			if (sb.indexOf(c) < 0) { //new char
				sb.append(c);
				if (sb.length() > longestLength) longestLength++;
			} else { //cut off everything at the beginning until we see c
				sb = sb.delete(0, sb.indexOf(c) + 1);
				sb.append(c);
			}
		}

		return longestLength;
	}

	public static void main (String[] args) {
		lengthOfLongestSubstring("pwwkew");
	}
}
