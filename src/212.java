import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class WordSearchII {
	public List<String> findWords(char[][] board, String[] words) {
		if (board == null || board.length == 0 || board[0].length == 0) return new ArrayList();

		List<String> res = new LinkedList<>();
		TrieNode t = new TrieNode();
		for (String word : words) {
			t.addWord(word);
		}

		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				char first = board[row][col];
				dfs(board, t, row, col, res);
			}
		}

		return res;
	}

	private void dfs(char[][] board, TrieNode t, int row, int col, List<String> res) {
		if (t == null) return;
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return;
		if (board[row][col] == '@') return;

		char c = board[row][col];
		board[row][col] = '@'; //visit
		t = t.next[c - 'a'];
		if (t != null && t.word != null) {
			res.add(t.word);
			t.word = null; // prevent returning duplicates in the end in this way, instead of using a set
		}

		dfs(board, t, row-1, col, res);
		dfs(board, t, row+1, col, res);
		dfs(board, t, row, col-1, res);
		dfs(board, t, row, col+1, res);


		board[row][col] = c; //backtrack
	}


	class TrieNode {
		TrieNode[] next = new TrieNode[26];
		String word;

		public void addWord(String word) {
			TrieNode curr = this;
			for (char c : word.toCharArray()) {
				if (curr.next[c - 'a'] == null) {
					curr.next[c - 'a'] = new TrieNode();
				}
				curr = curr.next[c - 'a'];
			}
			curr.word = word;
		}
	}
}


