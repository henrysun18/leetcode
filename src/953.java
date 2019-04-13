class VerifyingAnAlienDictionary {
	public boolean isAlienSorted(String[] words, String order) {
		//for each first letter of the words in words array, check indexOf it and compare with previous
		String prevWord = "";
		for (String word : words) {
			if (wordsAreInOrder(prevWord, word, order)) {
				prevWord = word;
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean wordsAreInOrder(String word1, String word2, String order) {
		//make sure to cover these edge cases that the for loop can't handle
		//two equal words should return true btw
		if (word2.startsWith(word1)) {
			return true;
		}
		if (word1.startsWith(word2)) {
			return false;
		}

		//compare each char while asserting word1's is less
		//by now it's guaranteed that at some point in both words, the letters will be different
		//so we don't have to deal with indexoutofbounds
		for (int i = 0; i < word1.length(); i++) {
			int char1Order = order.indexOf(word1.charAt(i));
			int char2Order = order.indexOf(word2.charAt(i));

			//forgot to also break early with this check...
			if (char1Order < char2Order) {
				return true;
			}
			//forgot that we're returning false for char1Order > char2Order and not the other way around...
			if (char1Order > char2Order) {
				return false;
			}
		}

		return true;
	}
}
