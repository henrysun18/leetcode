class HouseRobberII {
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		if (nums.length == 2) return Math.max(nums[0], nums[1]);
		//if length is 2, can still rob one even if they're arranged "in a circle"


		//need to try robbing from nums[0] to nums[length-2]
		// then try robbing from nums[1] to nums[length-1]
		int lootExcludingLast = rob(nums, 0);
		int lootExcludingFirst = rob(nums, 1);
		return Math.max(lootExcludingLast, lootExcludingFirst);
	}

	private int rob(int[] nums, int start) {
		int[] dp = new int[nums.length];
		dp[start] = nums[start];
		dp[start+1] = Math.max(nums[start], nums[start+1]);
		for (int i = start+2; i < start + nums.length-1; i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
		}

		return dp[start + nums.length-2]; //NOT dp[nums.length-1] since we might stop at nums.length-1 - 1
	}
}
