package RegularExpressionMatching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Henry on 5/30/2018.
 * Problem 10
 */
public class Solution {

	public static boolean isMatch(String s, String p) {
		boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
		dp[s.length()][p.length()] = true;

		for (int i = s.length(); i >= 0; i--) { //need to test with empty s since s="" p="c" should be false
			for (int j = p.length()-1; j >= 0; j--) {
				boolean isFirstCharacterMatchPossible = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
				if (j+1 < p.length() && p.charAt(j+1) == '*') {
					dp[i][j] = dp[i][j+2] || isFirstCharacterMatchPossible && dp[i+1][j];
				} else {
					dp[i][j] = isFirstCharacterMatchPossible && dp[i+1][j+1];
				}
			}
		}

		return dp[0][0];
	}

	public static void main(String[] args) {
		System.out.println(true == isMatch("abbbcd", "ab*bbbcd"));
		System.out.println(false == isMatch("mississippi", "mis*is*p*."));
		System.out.println(false == isMatch("bbab", "b*a*"));
		System.out.println(true == isMatch("aa", "a*"));
		System.out.println(true == isMatch("aaa", "a*a"));
		System.out.println(true == isMatch("aab", "c*a*b*"));
		System.out.println(true == isMatch("aab", "c*a*b"));
		System.out.println(false == isMatch("ab", ".*c"));
	}
}
