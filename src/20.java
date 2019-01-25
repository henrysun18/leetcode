import java.util.Stack;

/**
 * Problem 20
 */
class ValidParentheses {
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		String open = "{([";
		String closed = "})]";

		for (char c : s.toCharArray()) {
			if (closed.indexOf(c) >= 0) {
				char correspondingOpenParen = open.charAt(closed.indexOf(c));
				if (stack.empty() || stack.peek() != correspondingOpenParen) {
					return false; //unexpected closing parenthesis
				} else {
					stack.pop(); //expected closing parenthesis
				}
			} else {
				stack.add(c);
			}
		}

		return stack.empty();
	}
}
