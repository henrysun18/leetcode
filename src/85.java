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
			// now the objective is for every row, find the greatest consecutive chain of numbers where length * (min number in chain) = max value
			// if chain is 1,5,4,1,5,2,0,7,1 we want max = 2*4 instead of 2*2 or 1*6 or 1*7
			// can easily accomplish this small task in O(n^2) time and simply trying all chains
			// can we do this in O(n) time????
			//dont think so...
			for (int i = 0; i < dp[0].length; i++) {
				int minHeight = Integer.MAX_VALUE;
				int length = 0;
				for (int j = i; j < dp[0].length; j++) {
					if (dp[row][j] == 0) {
						length = 0;
						minHeight = Integer.MAX_VALUE;
					} else {
						length++;
						minHeight = Math.min(dp[row][j], minHeight);
						if (length * minHeight > maxRectangle) {
							maxRectangle = length * minHeight;
						}
					}
				}
			}
		}

		return maxRectangle;
	}
}
