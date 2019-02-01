//problem 38
class CountAndSay {
	public String countAndSay(int n) {
		int current = 1;

		String previousTerm = "1";

		while (current < n) {
			StringBuilder currentTerm = new StringBuilder();

			char currentChar = previousTerm.charAt(0);
			int currentCharCount = 1;
			for (int i = 1; i < previousTerm.length(); i++) {
				if (previousTerm.charAt(i) == currentChar) {
					currentCharCount++;
				} else {
					currentTerm.append(currentCharCount);
					currentTerm.append(currentChar);
					currentChar = previousTerm.charAt(i);
					currentCharCount = 1;
				}
			}
			currentTerm.append(currentCharCount);
			currentTerm.append(currentChar);

			previousTerm = currentTerm.toString();
			current++;
		}

		return previousTerm;
	}
}
