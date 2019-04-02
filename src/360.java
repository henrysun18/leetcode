class SortTransformedArray {
	public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
		// = (ax + b)x + c
		// [-4, -2, 2, 4] -> [9, 3, 15, 33]


		//for any quadratic function, the x's are either going to be increasing or decreasing since there's only one minimum/maximum
		//we're not sure where the min/max is, so we'll need to plug it in
		//if nums array is sorted, then we'll eventually find the point when it changes from increasing->decreasing or vice versa
		//4 cases: resultant array is only increasing ->return!
		//          resultant iarray is only decreasing -> reverse and return!
		//          resultant array is increasing first then decreasing -> get sorted version by using two pointers
		//          resultant array is decreasing first then increasing -> get sorted version by using two pointers
		int[] res = new int[nums.length];

		//first replace stuff in nums array with the result of applying the formula
		for (int i = 0; i < nums.length; i++) {
			nums[i] = a*nums[i]*nums[i] + b*nums[i] + c;
		}
		if (nums.length == 1) return nums;

		//now use two pointers to populate res array. first scan to find the min/max if applicable
		boolean increasing = a < 0; //don't worry about a == 0 case, we'll just never decrement right pointer

		//don't need to find midpoint. just always have left = 0. if there's a max then fill res from left to right. if there's a min then fill res from right to left
		int left = 0, right = nums.length-1;
		for (int i = 0; i < nums.length; i++) {
			if (increasing) {
				//populate res array from left to right, since left/right are competing for minimum
				res[i] = nums[left] < nums[right] ? nums[left++] : nums[right--];
			} else {
				//populate res array from right to left, since left/right are competing for maximum
				res[nums.length-1-i] = nums[left] > nums[right] ? nums[left++] : nums[right--];
			}
		}
		return res;
	}
}
