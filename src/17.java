import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Problem 17
 */

class LetterCombinationsOfAPhoneNumber {
	public List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			return new LinkedList<>();
		}

		List<String> result = new LinkedList<>();

		Map<Integer, String> map = new HashMap<>();
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");

		int d = 0;
		char[] word = new char[digits.length()];

		findCombinations(digits, map, d, word, result);
		return result;
	}

	private void findCombinations(String digits, Map<Integer, String> map, int d, char[] word, List<String> result) {
		if (d == digits.length()) {
			result.add(String.valueOf(word));
		} else {
			String mapping = map.get(Integer.parseInt(digits.substring(d, d+1)));
			for (char c : mapping.toCharArray()) {
				word[d] = c;
				findCombinations(digits, map, d+1, word, result);
			}
		}
	}
}
