import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordBreak {
	public boolean wordBreak(String s, List<String> wordDict) {
		Set<String> dict = new HashSet<>(wordDict);
		boolean[] canBreak = new boolean[s.length()+1];
		canBreak[s.length()] = true; //deal with checking last letter

		evaluate(s, dict, canBreak);

		return canBreak[0];
	}

	private void evaluate(String s, Set<String> dict, boolean[] canBreak) {
		// n^2 time
		for (int i = s.length()-1; i >= 0; i--) {
			for (int j = i+1; j <= s.length(); j++) {
				String strToJ = s.substring(i, j);
				if (dict.contains(strToJ) && canBreak[j]) {
					canBreak[i] = true;
					break; //don't need to keep checking for current i, move on and decrement i
				}
			}
		}
	}
}
