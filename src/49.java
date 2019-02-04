import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		// hashmap of key -> list of all anagrams
		// key can be the sorted version of a word

		Map<String, List<String>> map = new HashMap<>();
		for (String str : strs) {
			String key = getKey(str);
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(str);
		}

		List<List<String>> result = new LinkedList<>(map.values());

		return result;
	}

	private String getKey(String str) {
		char[] strChars = str.toCharArray();
		Arrays.sort(strChars);
		return String.valueOf(strChars);
	}
}
