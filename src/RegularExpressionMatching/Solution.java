package RegularExpressionMatching;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by Henry on 5/30/2018.
 * Problem 10
 */
public class Solution {

	public static boolean isMatch(String s, String p) {
		if (s.equals("") && p.equals("")) {
			return true;
		} else if (!s.equals("") && p.equals("")) { //still have chars left unconsumed
			return false;
		}

		if (expectSpecificCharacterOnce(s, p)) {
			if (!s.equals("") && s.charAt(0) == p.charAt(0)) {
				return isMatch(s.substring(1), p.substring(1));
			} else {
				return false;
			}
		} else if (expectAnySingleCharacterOnce(s, p)) {
			return !s.equals("") && isMatch(s.substring(1), p.substring(1));
		} else {
			char precedingCharToConsume = p.charAt(0);
			boolean precedingCharIsWild = precedingCharToConsume == '.';
			Stack possibleNextCharStartIndices = new Stack();

			possibleNextCharStartIndices.add(0);
			for (int i = 0; i < s.length(); i++) {
				if (precedingCharIsWild) {
					possibleNextCharStartIndices.add(i+1);
				} else if (s.charAt(i) == precedingCharToConsume) {
					possibleNextCharStartIndices.add(i+1);
				} else {
					break; //can't consume any further
				}
			}

			while (possibleNextCharStartIndices.size() > 0) { //start popping and backtrack if necessary
				int possibleStartIndexOfCharAfterMatching = (int) possibleNextCharStartIndices.pop();
				if (isMatch(s.substring(possibleStartIndexOfCharAfterMatching), p.substring(2))) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean expectSpecificCharacterOnce(String s, String p) {
		return p.charAt(0) != '.' && (p.length() == 1 || p.charAt(1) != '*');
	}

	private static boolean expectAnySingleCharacterOnce(String s, String p) {
		return p.charAt(0) == '.' && (p.length() == 1 || p.charAt(1) != '*');
	}

	public static void main(String[] args) {
		System.out.println(true == isMatch("abbbcd", "ab*bbbcd"));
		System.out.println(false == isMatch("mississippi", "mis*is*p*."));
		System.out.println(false == isMatch("bbab", "b*a*"));
		System.out.println(true == isMatch("aa", "a*"));
		System.out.println(true == isMatch("aaa", "a*a"));
		System.out.println(true == isMatch("aab", "c*a*b*"));
		System.out.println(true == isMatch("aab", "c*a*b"));
	}
}
