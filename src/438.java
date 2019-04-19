import java.util.ArrayList;
import java.util.List;

class FindAllAnagramsInAString {
	public List<Integer> findAnagrams(String s, String p) {
		//don't want to repeatedly call isAnagram on two potentially long strings
		//let's just build map, then shift window


		List<Integer> res = new ArrayList<>();
		if (s.length() < p.length()) return res;

		int[] chars = new int[26];
		for (char c : p.toCharArray()) {
			chars[c-'a']++;
		}

		int left = 0, right = 0;
		while (right < p.length()) {
			char c = s.charAt(right);
			chars[c-'a']--;
			right++;
		}
		if (hasAllZeros(chars)) {
			res.add(left);
		}

		while (right < s.length()) {
			//shift window to the right
			char l = s.charAt(left++);
			chars[l-'a']++;

			//right is already shifted and pointing at the next value, just post increment
			char r = s.charAt(right++);
			chars[r-'a']--;

			if (hasAllZeros(chars)) {
				res.add(left);
			}
		}
		return res;
	}

	private boolean hasAllZeros(int[] chars) {
		for (int count : chars) {
			if (count != 0) return false;
		}
		return true;
	}
}
