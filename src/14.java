/**
 * Problem 14
 */
class LongestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int lengthOfPrefix = -1;
		while (true) {
			lengthOfPrefix++;
			if (strs[0].length() <= lengthOfPrefix) {
				return strs[0].substring(0, lengthOfPrefix);
			}
			char c = strs[0].charAt(lengthOfPrefix);
			for (String str : strs) {
				if (str.length() <= lengthOfPrefix) {
					return str.substring(0, lengthOfPrefix);
				}
				if (str.charAt(lengthOfPrefix) != c) {
					return str.substring(0, lengthOfPrefix);
				}
			}
		}
	}
}
