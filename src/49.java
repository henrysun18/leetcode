import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class GroupAnagrams {
	public List<List<String>> groupAnagrams(String[] strs) {
		//maybe keep a hashmap of sorted str representation to a list of strs from teh array?
		//we have to sort all the strings; for length s, slgs time, where we could just instead count the letters in s time
		Map<String, List<String>> groups = new HashMap<>();

		for (String str : strs) {
			String identifier = getIdentifier(str);
			groups.putIfAbsent(identifier, new LinkedList<>());
			groups.get(identifier).add(str);
		}
		List<List<String>> res = new ArrayList<>(groups.values());
		return res;
	}

	private String getIdentifier(String str) {
		int[] charCounts = new int[26];
		for (char c : str.toCharArray()) {
			charCounts[c-'a']++;
		}
		StringBuilder sb = new StringBuilder();
		for (int count : charCounts) {
			sb.append("#");
			sb.append(count);
		}
		return sb.toString();
	}
}
