import java.util.Stack;

class FloodFill {
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		//just dfs / bfs iteratively
		int oldColor = image[sr][sc];

		//if old == new, then no changes are needed
		if (oldColor == newColor) return image;

		Stack<int[]> stack = new Stack<>(); //int[] is a 2 element array with row and col of the coordinate to check/fill
		stack.push(new int[]{sr, sc});

		while (!stack.isEmpty()) {
			int[] coordinate = stack.pop();

			if (isValid(image, coordinate) && image[coordinate[0]][coordinate[1]] == oldColor) {
				image[coordinate[0]][coordinate[1]] = newColor;

				stack.push(new int[]{coordinate[0] + 1, coordinate[1]});
				stack.push(new int[]{coordinate[0] - 1, coordinate[1]});
				stack.push(new int[]{coordinate[0], coordinate[1] + 1});
				stack.push(new int[]{coordinate[0], coordinate[1] - 1});
			}
		}

		return image;
	}

	private boolean isValid(int[][] image, int[] coordinate) {
		return coordinate[0] >= 0 && coordinate[0] < image.length && coordinate[1] >= 0 && coordinate[1] < image[0].length;
	}
}
