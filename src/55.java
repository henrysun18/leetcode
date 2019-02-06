class JumpGame {
	public boolean canJump(int[] nums) {
		//simply have an array of length nums.length symbolizing which places we can jump to?
		//can do recursion, where we try all jumps for each position, but its n^2 time
		//store a counter that holds the index of the farthest we currently know we can jump to, and iterate through entire array
		if (nums.length <= 1) {
			return true;
		}

		int farthestKnownJump = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] + i > farthestKnownJump) {
				farthestKnownJump = i + nums[i];
			}
			if (farthestKnownJump >= nums.length-1) {
				return true;
			}
			if (farthestKnownJump == i) {
				return false; //not allowed to increment i anymore
			}
		}

		return false;
	}
}
