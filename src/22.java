//Problem 22

import java.util.LinkedList;
import java.util.List;

class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> result = new LinkedList<>();
		char[] combination = new char[n*2];
		generateParentheses(result, combination, 0, 0, n);

		return result;
	}

	private void generateParentheses(List<String> result, char[] combination, int currentChar, int numOpenAvailable, int numOpenRemaining) {
		if (currentChar == combination.length) {
			result.add(String.valueOf(combination));
			return;
		}

		if (numOpenRemaining > 0) {
			combination[currentChar] = '(';
			generateParentheses(result, combination, currentChar+1, numOpenAvailable+1, numOpenRemaining-1);
		}
		if (numOpenAvailable > 0) {
			combination[currentChar] = ')';
			generateParentheses(result, combination, currentChar+1, numOpenAvailable-1, numOpenRemaining);
		}
	}
}
