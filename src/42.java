class TrappingRainWater {
	public int trap(int[] height) {

		// for each spot in the middle (not index 0 or index height.length-1)
		// scan left and scan right to find the highest on the left and highest on the right

		int trapped = 0;

		for (int i = 1; i < height.length-1; i++) {
			int maxLeft = i-1;
			int maxRight = i+1;

			for (int left = i-1; left >= 0; left--) {
				if (height[left] > height[maxLeft]) {
					maxLeft = left;
				}
			}
			for (int right = i+1; right < height.length; right++) {
				if (height[right] > height[maxRight]) {
					maxRight = right;
				}
			}

			int maxHeightAround = Math.min(height[maxLeft], height[maxRight]);
			if (maxHeightAround > height[i]) {
				trapped += maxHeightAround - height[i];
			}
		}

		return trapped;
	}


	public int trapWithDP(int[] height) {
		// initialize maxLeft[] array and maxRight[] array so we don't have to scan all the way left/right every time
		int[] maxLefts = new int[height.length];
		int[] maxRights = new int[height.length];
		int currentMaxLeft = 0;
		int currentMaxRight = 0;

		for (int i = 1; i < maxLefts.length; i++) {
			if (height[i-1] > currentMaxLeft) {
				currentMaxLeft = height[i-1];
			}
			maxLefts[i] = currentMaxLeft;
		}
		for (int i = maxRights.length - 2; i >= 0; i--) {
			if (height[i+1] > currentMaxRight) {
				currentMaxRight = height[i+1];
			}
			maxRights[i] = currentMaxRight;
		}

		int trapped = 0;
		for (int i = 1; i < height.length-1; i++) {
			int maxHeightAround = Math.min(maxLefts[i], maxRights[i]);
			if (maxHeightAround > height[i]) {
				trapped += maxHeightAround - height[i];
			}
		}
		return trapped;
	}
}
