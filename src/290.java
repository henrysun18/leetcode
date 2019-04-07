import java.util.HashMap;
import java.util.Map;

class WordPattern {
	public boolean wordPattern(String pattern, String str) {
		String[] words = str.split(" ");

		Map<Character, String> symbolToWord = new HashMap<>();
		Map<String, Character> wordToSymbol = new HashMap<>();

		//edge case
		if (pattern.length() != words.length) return false;

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			Character symbol = pattern.charAt(i);
			//return false when symbol is mapped to something else
			if (symbolToWord.get(symbol) != null && !word.equals(symbolToWord.get(symbol))) {
				return false;
			}
			//return false when word is already mapped to different symbol
			if (wordToSymbol.get(word) != null && !symbol.equals(wordToSymbol.get(word))) {
				return false;
			}
			symbolToWord.put(symbol, word);
			wordToSymbol.put(word, symbol);
		}
		return true;
	}
}
