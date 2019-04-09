import java.util.Arrays;

class PourWater {
	public int[] pourWater(int[] heights, int V, int K) {
		//brute force is V*heights.length, where we just follow the water drop for each drop
		//is it possible to use space to bring this to O(V) or to O(heights.length) ?
		//maybe we can have stack of indices where the water can flow into (one for each side)
		//the stack must be the lowest size to represent the holes that the water will go into
		//once the stack is empty, try to form a new stack now that the holes are filled
		//do this for both sides until no new stack can be formed
		//only then do we increase V

		//worst case is still O(V*N) even with the stack, so why not just do what the discuss is doing and just do this manually
		if (heights == null || heights.length == 0) return new int[0];

		int[] res = Arrays.copyOf(heights, heights.length);

		while (V > 0) {
			V--;

			//look for left hole
			int leftHole = findHole(res, K, -1);
			if (leftHole != -1) {
				res[leftHole]++;
				continue;
			}

			int rightHole = findHole(res, K, 1);
			if (rightHole != -1) {
				res[rightHole]++;
				continue;
			}

			res[K]++;
		}

		return res;
	}

	private int findHole(int[] heights, int K, int direction) {
		int hole = -1;
		for (int i = K+direction; i >= 0 && i < heights.length; i += direction) {
			if (hole == -1) {
				if (heights[i] < heights[K]) {
					hole = i;
				} else if (heights[i] > heights[K]) {
					return -1;
				}
			} else {
				if (heights[i] < heights[hole]) {
					hole = i;
				} else if (heights[i] > heights[hole]) {
					return hole;
				}
			}
		}

		return hole; //really only goign to be reached after going left all the way to 0 and it's deepest at 0
	}
}
