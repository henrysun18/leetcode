class MaximalRectangle {
	public int maximalRectangle(char[][] matrix) {
		// have a nxm size auxiliary matrix where each cell shows how many 1's are in that cell + connected 1's below
		// go through the auxiliary matrix in linear time m times (n*m again) while keeping track of the largest rectangle
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}

		int[][] dp = new int[matrix.length][matrix[0].length];

		//for all columns, start from bottom and fill up dp
		for (int col = 0; col < matrix[0].length; col++) {
			int chain = 0;
			for (int row = matrix.length-1; row >= 0; row--) {
				if (matrix[row][col] == '1') {
					chain++;
					dp[row][col] = chain;
				} else {
					chain = 0;
				}
			}
		}

		//debug dp matrix by printing
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(dp[i][j]);
			}
			System.out.println();
		}

		//go through dp row by row while keeping track of largest rectangle
		int maxRectangle = 0;

		for (int row = 0; row < dp.length; row++) {
			int minHeight = Integer.MAX_VALUE; //reset these counters every row
			int length = 0;

			for (int col = 0; col < dp[0].length; col++) {
				int curr = dp[row][col];
				if (curr == 0) { // check for == 0 since it's a matrix of integers, don't check for '0' lol.....
					length = 0;
					minHeight = Integer.MAX_VALUE;
				} else {
					minHeight = Math.min(minHeight, curr);
					length++;
					int currRectSize = minHeight * length;
					System.out.println(minHeight + ":" + length);
					if (currRectSize > maxRectangle) {
						maxRectangle = currRectSize;
					}
				}
			}
		}

		return maxRectangle;
	}
}
