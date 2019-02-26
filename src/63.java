class UniquePathsII {
	public int uniquePathsWithObstaclesBottomUp(int[][] obstacleGrid) {

		if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 0; //or ask if it should be set to 1
		}

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] numPaths = new int[m][n];

		if (obstacleGrid[m-1][n-1] == 1) {
			return 0;
		} else {
			numPaths[m-1][n-1] = 1;
		}

		for (int i = m-2; i >= 0; i--) {
			if (obstacleGrid[i][n-1] == 1) {
				//is obstacle
				break;
			} else {
				numPaths[i][n-1] = 1;
			}
		}

		for (int i = n-2; i >= 0; i--) {
			if (obstacleGrid[m-1][i] == 1) {
				break;
			} else {
				numPaths[m-1][i] = 1;
			}
		}

		for (int row = m-2; row >= 0; row--) {
			for (int col = n-2; col >= 0; col--) {
				if (obstacleGrid[row][col] == 1) {
					numPaths[row][col] = 0;
				} else {
					numPaths[row][col] = numPaths[row+1][col] + numPaths[row][col+1];
				}
			}
		}

		return numPaths[0][0];
	}







	
	public int uniquePathsWithObstaclesTopDown(int[][] obstacleGrid) {
		if (obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
			return 0;
		}
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;

		int[][] numPaths = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				numPaths[i][j] = -1;
			}
		}

		return numPaths(obstacleGrid, 0, 0, numPaths);
	}
	private int numPaths(int[][] grid, int row, int col, int[][] numPaths) {
		if (row >= grid.length || col >= grid[0].length) {
			return 0;
		}
		if (row == grid.length-1 && col == grid[0].length-1) {
			return 1 ^ grid[row][col];
		}
		if (numPaths[row][col] != -1) {
			return numPaths[row][col];
		}
		if (grid[row][col] == 1) {
			numPaths[row][col] = 0;
			return 0;
		}

		int paths = numPaths(grid, row+1, col, numPaths) + numPaths(grid, row, col+1, numPaths);
		numPaths[row][col] = paths;
		return paths;
	}
}
