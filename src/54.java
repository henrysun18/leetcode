import java.util.ArrayList;
import java.util.List;

class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		if (matrix.length == 0) {
			return result;
		}

		int colStart = 0;
		int colEnd = matrix[0].length - 1;
		int rowStart = 0;
		int rowEnd = matrix.length - 1;

		while (colStart <= colEnd && rowStart <= rowEnd) {
			for (int col = colStart; col <= colEnd; col++) {
				result.add(matrix[rowStart][col]);
			}
			rowStart++;

			for (int row = rowStart; row <= rowEnd; row++) {
				result.add(matrix[row][colEnd]);
			}
			colEnd--;

			if (rowStart <= rowEnd) { //check this condition again, as rowStart++ may have invalidated the need for the next for loop (i.e. the right-to-left sweep)
				for (int col = colEnd; col >= colStart; col--) {
					result.add(matrix[rowEnd][col]);
				}
				rowEnd--;
			}

			if (colStart <= colEnd) { //check this condition again, as colEnd-- may have invalidated the need for the next for loop (i.e. the bottom-up sweep)
				for (int row = rowEnd; row >= rowStart; row--) {
					result.add(matrix[row][colStart]);
				}
				colStart++;
			}
		}

		return result;
	}
}
