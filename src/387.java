import java.util.HashMap;
import java.util.Map;

class FirstUniqueCharacterInAString {
	public int firstUniqCharBad(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}

		int res = 0;
		Map<Character, Integer> firstIndexOfCharMap = new HashMap<>();
		boolean[] unique = new boolean[s.length()];

		//initialize unique array elements to true
		for (int i = 0; i < unique.length; i++) {
			unique[i] = true;
		}

		//check if any unique array elements needs to be set false
		for (int i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);
			if (firstIndexOfCharMap.containsKey(c)) {
				unique[i] = false;
				unique[firstIndexOfCharMap.get(c)] = false;
			} else {
				firstIndexOfCharMap.put(c, i);
			}
		}

		//look for first unique element index
		for (int i = 0; i < unique.length; i++) {
			if (unique[i]) {
				return i;
			}
		}

		return -1;
	}

	public int firstUniqChar(String s) {
		if (s == null || s.length() == 0) {
			return -1;
		}

		int[] charsUsed = new int[128];
		for (int i = 0; i < s.length(); i++) {
			charsUsed[s.charAt(i)]++;
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (charsUsed[c] == 1) {
				return i;
			}
		}
		return -1;
	}
}
