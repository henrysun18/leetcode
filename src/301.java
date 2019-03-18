import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		// brute force is to generate all subsets then validate parentheses on at most all of them
		// break early as soon as we see a bad paren, like (()))....


		List<String> res = new ArrayList<>();

		// generate all subsets while repopulating res list if we get equally long / longer valid string
		getLongestSubsets(s.toCharArray(), 0, 0, new StringBuilder(), res);

		return new ArrayList<>(new HashSet<>(res)); //remove duplicates by convert to set and back to list lol
	}

	private void getLongestSubsets(char[] chs, int numOpenParens, int curr, StringBuilder expr, List<String> res) {
		if (curr == chs.length) {
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

		char c = chs[curr];
		if (c == '(') {
			//don't add (
			getLongestSubsets(chs, numOpenParens, curr+1, expr, res);

			//add (
			expr.append(c);
			getLongestSubsets(chs, numOpenParens+1, curr+1, expr, res);
			backtrack(expr);

		} else if (c == ')') {
			if (numOpenParens > 0) {
				// add )
				expr.append(c);
				getLongestSubsets(chs, numOpenParens-1, curr+1, expr, res);
				backtrack(expr);
			}
			//dont add )
			getLongestSubsets(chs, numOpenParens, curr+1, expr, res);

		} else {
			//always add non parens
			expr.append(c);
			getLongestSubsets(chs, numOpenParens, curr+1, expr, res);
			backtrack(expr);
		}
	}

	private void backtrack(StringBuilder expr) {
		if (expr.length() > 0) {
			expr.deleteCharAt(expr.length()-1); //backtrack
		}
	}
}
