class MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int[] mins = new int[nums.length]; //each element is the min product if subarray starts from i
		int[] maxes = new int[nums.length]; //each element is the max product if subarray starts from i

		int last = nums[nums.length-1];
		if (last < 0) {
			mins[nums.length-1] = last;
		} else {
			maxes[nums.length-1] = last;
		}

		int maxProduct = 0;

		for (int i = nums.length-2; i >= 0; i--) {
			int curr = nums[i];
			if (curr < 0) {
				mins[i] = Math.min(curr * maxes[i+1], curr);
				maxes[i] = curr * mins[i+1];
			} else if (curr > 0) {
				mins[i] = curr * mins[i+1];
				maxes[i] = Math.max(curr, curr * maxes[i+1]);
			}
		}

		for (int max : maxes) {
			if (max > maxProduct) {
				maxProduct = max;
			}
		}

		return maxProduct;
	}
}
