class HouseRobber {
	public int rob(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];

		int[] robbed = new int[nums.length];
		robbed[0] = nums[0];
		robbed[1] = Math.max(nums[0], nums[1]); //[2,1,1,2] tripped me up, originally had robbed[1] = nums[1] which doesn't make sense
		for (int i = 2; i < nums.length; i++) {
			robbed[i] = Math.max(robbed[i-2] + nums[i], robbed[i-1]);
		}

		return robbed[nums.length-1];
	}
}
