import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
		if (board == null || board.length == 0 || board[0].length == 0) return new ArrayList();

		// pruning dead paths as early as possible is key
		Set<String> path = new HashSet<>();
		Set<String> dict = new HashSet<>(Arrays.asList(words));
		Set<String> res = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				findWords(board, dict, row, col, sb, path, res);
			}
		}

		return new ArrayList<>(res);
	}

	private void findWords(char[][] board, Set<String> dict, int row, int col, StringBuilder sb, Set<String> path, Set<String> res) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;
		if (res.size() == dict.size()) return;

		String key = row+":"+col;
		if (path.contains(key)) return;

		//dfs logic, add curr to word first
		sb.append(board[row][col]);
		path.add(key);
		String word = sb.toString();

		//ready to add word to path
		int originalResSize = res.size();

		//continue dfs?
        /*boolean shouldPrune = true;
        for (String s : dict) {
            if (s.startsWith(word)) {
                shouldPrune = false;
            }
        }
        if (!shouldPrune) {*/
		if (dict.contains(word)){
			res.add(word);
			//still need to continue dfs in case we get a longer word
		}
		findWords(board, dict, row-1, col, sb, path, res);
		findWords(board, dict, row+1, col, sb, path, res);
		findWords(board, dict, row, col-1, sb, path, res);
		findWords(board, dict, row, col+1, sb, path, res);
		//}

		//backtrack
		sb.deleteCharAt(sb.length()-1);
		path.remove(key);
	}
}
