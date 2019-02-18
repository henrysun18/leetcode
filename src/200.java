class NumberOfIslands {
	public int numIslands(char[][] grid) {

		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		boolean[][] visited = new boolean[grid.length][grid[0].length];
		int islands = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '0') continue;

				// if not visited, increment then perform dfs
				if (!visited[i][j]) {
					islands++;
					dfs(grid, i, j, visited);
				}
			}
		}

		return islands;
	}

	private void dfs(char[][] grid, int row, int col, boolean[][] visited) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
			return;
		}
		if (grid[row][col] == '0') {
			return;
		}
		if (!visited[row][col]) {
			visited[row][col] = true;
			dfs(grid, row-1, col, visited);
			dfs(grid, row+1, col, visited);
			dfs(grid, row, col-1, visited);
			dfs(grid, row, col+1, visited);
		}
	}
}
