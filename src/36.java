import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//problem 36
class ValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		// 27 hashsets in total
		List<HashSet<Character>> rows = new ArrayList<>();
		List<HashSet<Character>> columns = new ArrayList<>();
		List<List<HashSet<Character>>> boxes = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			rows.add(new HashSet<>());
			columns.add(new HashSet<>());
		}
		for (int i = 0; i < 3; i++) {
			boxes.add(new ArrayList<>());
			for (int j = 0; j < 3; j++) {
				boxes.get(i).add(new HashSet<>());
			}
		}
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				char value = board[row][col];
				if (value == '.') continue;

				if (rows.get(row).contains(value)) {
					return false;
				} else {
					rows.get(row).add(value);
				}

				if (columns.get(col).contains(value)) {
					return false;
				} else {
					columns.get(col).add(value);
				}

				if (boxes.get(row/3).get(col/3).contains(value)) {
					return false;
				} else {
					boxes.get(row/3).get(col/3).add(value);
				}
			}
		}

		return true;
	}
}
