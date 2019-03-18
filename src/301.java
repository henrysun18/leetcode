import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		// brute force is to generate all subsets then validate parentheses on at most all of them
		// break early as soon as we see a bad paren, like (()))....
		int misplacedLeft = 0;
		int misplacedRight = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				misplacedLeft++;
			} else if (c == ')') {
				if (misplacedLeft == 0) {
					misplacedRight++;
				} else {
					misplacedLeft--;
				}
			}
		}
		// now we know that we can only discard up to misplacedLeft amount of ( and misplacedRight amount of )

		List<String> res = new ArrayList<>();

		// generate all subsets while repopulating res list if we get equally long / longer valid string
		getLongestSubsets(s, 0, 0, misplacedLeft, misplacedRight, new StringBuilder(), res);

		return new ArrayList<>(new HashSet<>(res)); //remove duplicates by convert to set and back to list lol
	}

	private void getLongestSubsets(String s, int numOpenParens, int curr, int misplacedLeft, int misplacedRight, StringBuilder expr, List<String> res) {
		if (curr == s.length()) {
			if (numOpenParens > 0) return;
			// already validated by this point, so add this expr to res if it's long
			if (!res.isEmpty() && res.get(0).length() < expr.length()) {
				res.clear();
			}
			if (res.isEmpty() || res.get(0).length() == expr.length()) {
				res.add(expr.toString());
			}
			// backtrack(expr); don't need this line since we're handling backtracking during the recursive step already
			return;
		}

		char c = s.charAt(curr);
		if (c == '(') {
			//add (
			expr.append(c);
			getLongestSubsets(s, numOpenParens+1, curr+1, misplacedLeft, misplacedRight, expr, res);
			backtrack(expr);

			if (misplacedLeft > 0) {
				//don't add (
				getLongestSubsets(s, numOpenParens, curr+1, misplacedLeft-1, misplacedRight, expr, res);
			}
		} else if (c == ')') {
			if (numOpenParens > 0) {
				// add )
				expr.append(c);
				getLongestSubsets(s, numOpenParens-1, curr+1, misplacedLeft, misplacedRight, expr, res);
				backtrack(expr);
			}
			if (misplacedRight > 0) {
				//dont add )
				getLongestSubsets(s, numOpenParens, curr+1, misplacedLeft, misplacedRight-1, expr, res);
			}
		} else {
			//always add non parens
			expr.append(c);
			getLongestSubsets(s, numOpenParens, curr+1, misplacedLeft, misplacedRight, expr, res);
			backtrack(expr);
		}
	}

	private void backtrack(StringBuilder expr) {
		if (expr.length() > 0) {
			expr.deleteCharAt(expr.length()-1); //backtrack
		}
	}
}
