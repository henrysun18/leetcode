import java.util.HashMap;
import java.util.Map;

class AddAndSearchWord {

	private TrieNode root;

	/** Initialize your data structure here. */
	public AddAndSearchWord() {
		root = new TrieNode(false);
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);

			if (i == word.length() - 1) {
				if (!curr.children.containsKey(c)) {
					curr.children.put(c, new TrieNode(true));
				} else {
					curr.children.get(c).isWord = true;
				}

			} else {
				curr.children.putIfAbsent(c, new TrieNode(false));
				curr = curr.children.get(c);
			}
		}
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		return search(word, root);
	}

	private boolean search(String word, TrieNode root) {
		TrieNode curr = root;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (c == '.') {
				//search every child
				for (TrieNode child : curr.children.values()) {
					if (search(word.substring(i+1), child)) {
						return true;
					}
				}
				return false;
			}
			if (!curr.children.containsKey(c)) {
				return false;
			}

			curr = curr.children.get(c);
		}

		return curr.isWord;
	}
}

class TrieNode {
	public Map<Character, TrieNode> children;
	public boolean isWord;

	public TrieNode(boolean isWord) {
		this.isWord = isWord;
		this.children = new HashMap<>();
	}
}
