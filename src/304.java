class RangeSumQuery2DImmutable {
	private int[][] dp;

	public RangeSumQuery2DImmutable(int[][] matrix) {//NumMatrix(int[][] matrix) {
		//initialize 2 auxiliary matrices/arrays
		//rowSumFromLeft[i][j] -> sum of all elements in row i from left, up to and including col j
		//rowSums[i] -> sum of all elements in row i

		//dp approach where dp[i][j], gives the sum of all elements to the left and bottom
		int rows = matrix == null ? 0 : matrix.length+1;
		int cols = matrix != null && matrix.length > 0 ? matrix[0].length+1 : 0;

		this.dp = new int[rows][cols];
		for (int row = matrix.length-1; row >= 0; row--) {
			for (int col = matrix[0].length-1; col >= 0; col--) {
				dp[row][col] = matrix[row][col] + dp[row+1][col] + dp[row][col+1] - dp[row+1][col+1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		if (row1 >= dp.length-1 || row2 >= dp.length-1 || col1 >= dp[0].length-1 || col2 >= dp[0].length-1) return 0;

		return dp[row1][col1] - dp[row1][col2+1] - dp[row2+1][col1] + dp[row2+1][col2+1];
	}
}
