class ProductOfArrayExceptSelf {
	public int[] productExceptSelf(int[] nums) {
		if (nums == null) return null;
		if (nums.length == 0) return new int[0];

		int[] res = new int[nums.length];

		//use input array to store products of everything to the left
		//use output array to store products of everything to the right
		//iterate through output array, multiply in[i] * out[i]

		//first init output array with products of everything to right
		//if at the edge, assume product of outside of array = 1
		res[nums.length-1] = 1;
		for (int i = nums.length-2; i >= 0; i--) {
			res[i] = res[i+1] * nums[i+1];
		}

		//init input array with products of everything to the left
		int tmp = nums[0];
		nums[0] = 1;
		for (int i = 1; i < nums.length; i++) {
			int curr = nums[i];
			nums[i] = nums[i-1] * tmp;
			tmp = curr;
		}

		//finally, get results
		for (int i = 0; i < nums.length; i++) {
			res[i] = nums[i] * res[i];
		}

		return res;
	}
}
