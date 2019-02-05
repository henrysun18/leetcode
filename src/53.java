class MaximumSubarray {
	public int maxSubArray(int[] nums) {
		// assume nums.length > 0
		int maxSum = nums[0];
		int runningSum = 0;
		for (int num : nums) {
			runningSum += num;
			if (num > runningSum) {
				runningSum = num;
			}
			if (runningSum > maxSum) {
				maxSum = runningSum;
			}
		}

		return maxSum;
	}
}
