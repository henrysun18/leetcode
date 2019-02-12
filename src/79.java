class WordSearch {
	public boolean exist(char[][] board, String word) {
		char[] chars = word.toCharArray();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (lookFor(board, chars, 0, i, j)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean lookFor(char[][] board, char[] word, int curr, int row, int col) {
		if (curr == word.length) {
			return true;
		}
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return false;
		}

		char tmp = board[row][col];
		if (board[row][col] == word[curr]) {
			board[row][col] = ' '; //make sure we don't try to reuse it
		} else {
			return false;
		}

		//times out if we do boolean up = lookFor().... return up || down || left || right since there's no break early cases
		if (lookFor(board, word, curr+1, row-1, col)) {
			return true;
		}
		if (lookFor(board, word, curr+1, row+1, col)) {
			return true;
		}
		if (lookFor(board, word, curr+1, row, col-1)) {
			return true;
		}
		if (lookFor(board, word, curr+1, row, col+1)) {
			return true;
		}

		board[row][col] = tmp; //so we can backtrack

		return false;
	}
}
