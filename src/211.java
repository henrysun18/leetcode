import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class AddAndSearchWord {

	//don't go with tree structure, unnecessary dependency on a wrapper class
	private List<Map<Character, Boolean>> lettersAndTerminatorsAvailable;

	/** Initialize your data structure here. */
	public AddAndSearchWord() {
		lettersAndTerminatorsAvailable = new LinkedList<>();
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (lettersAndTerminatorsAvailable.size() == i) {
				lettersAndTerminatorsAvailable.add(new HashMap<>());
			}
			char c = word.charAt(i);
			if (c == '.') {
				continue;
			}
			if (i == word.length()-1) {
				lettersAndTerminatorsAvailable.get(i).put(c, true);
			} else {
				lettersAndTerminatorsAvailable.get(i).putIfAbsent(c, false); //wont override if already true
			}
		}
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		if (word == null) {
			return false;
		}
		if (word.length() > lettersAndTerminatorsAvailable.size()) {
			return false;
		}
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c == '.') {
				continue;
			}
			if (!lettersAndTerminatorsAvailable.get(i).containsKey(c)) {
				return false;
			}
		}
		int lastIndex = word.length()-1;
		return lettersAndTerminatorsAvailable.get(lastIndex).get(word.charAt(lastIndex)) == true;
	}
}
