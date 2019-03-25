class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int max = nums[0];

		int currMax = max;
		int currMin = max;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < 0) {
				// if -1,-2,-9,-6 then at the end of each for loop we have:
				// max = 2, min = -2
				// max = 18, min = -18
				// max = 108, min = -108
				int tmp = currMax;
				currMax = currMin;
				currMin = tmp;
			}

			currMax = Math.max(nums[i], currMax * nums[i]); //if curr num is positive, 2nd is best. if currnum is negative, currMax is negative now so 2nd is still positive!
			currMin = Math.min(nums[i], currMin * nums[i]);

			if (currMax > max) {
				max = currMax;
			}
		}

		return max;
	}
}
