class SetMatrixZeros {
	public void setZeroes(int[][] matrix) {
		// find first 0
		// use the row and column of that 0 to keep track of which rows and columns should be zero'd out
		int rowOfZero = -1;
		int colOfZero = -1;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				int element = matrix[i][j];
				if (element == 0) {
					// now we know which row and column of the matrix we can use to store
					rowOfZero = i;
					colOfZero = j;
					break;
				}
			}
			if (rowOfZero >= 0) {
				break;
			}
		}

		if (rowOfZero == -1) return;

		//go through each element in matrix and note down which rows and columns should be zeroed out
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				int element = matrix[row][col];
				if (element == 0) {
					matrix[rowOfZero][col] = 0;
					matrix[row][colOfZero] = 0;
				}
			}
		}

		for (int rowToSetZero = 0; rowToSetZero < matrix.length; rowToSetZero++) {
			if (rowToSetZero != rowOfZero && matrix[rowToSetZero][colOfZero] == 0) {
				for (int i = 0; i < matrix[0].length; i++) {
					matrix[rowToSetZero][i] = 0;
				}
			}
		}
		for (int colToSetZero = 0; colToSetZero < matrix[0].length; colToSetZero++) {
			if (colToSetZero != colOfZero && matrix[rowOfZero][colToSetZero] == 0) {
				for (int i = 0; i < matrix.length; i++) {
					matrix[i][colToSetZero] = 0;
				}
			}
		}
		//leave the row of zero and col of zero for last so we dont prematurely zero everything
		for (int i = 0; i < matrix.length; i++) {
			matrix[i][colOfZero] = 0;
		}
		for (int i = 0; i < matrix[0].length; i++) {
			matrix[rowOfZero][i] = 0;
		}
	}
}
