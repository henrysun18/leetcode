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
		//boolean increasing = nums[0] < nums[1]; //CANT ASSUME INCREASING OR DECREASING BY JUST FIRST TWO ELEMENTS; SORTED DOES NOT MEAN ALL UNIQUE!!
		boolean increasing = true;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i-1]) {
				increasing = false;
				break;
			} else if (nums[i] > nums[i-1]) { //also need this else if so we can verify increasing == true
				break;
			}
		}
		int midpointIndex = -1;
		for (int i = 2; i < nums.length; i++) {
			if (increasing && nums[i-1] > nums[i]) {
				midpointIndex = i-1;
				break;
			} else if (!increasing && nums[i-1] < nums[i]) {
				midpointIndex = i-1;
				break;
			}
		}

		//case 1-4
		if (increasing && midpointIndex == -1) {
			return nums;
		} else if (!increasing && midpointIndex == -1) {
			//reverse
			for (int i = 0; i < nums.length/2; i++) {
				int tmp = nums[i];
				nums[i] = nums[nums.length-1-i];
				nums[nums.length-1-i] = tmp;
			}
			return nums;
		} else if (increasing) {
			int left = 0;
			int right = nums.length-1;
			for (int i = 0; i < nums.length; i++) {
				if (nums[left] < nums[right]) {
					res[i] = nums[left];
					left++;
				} else {
					res[i] = nums[right];
					right--;
				}
			}
		} else {
			int left = midpointIndex;
			int right = midpointIndex+1;
			for (int i = 0; i < nums.length; i++) {
				if (right >= nums.length || left >= 0 && nums[left] < nums[right]) { //check right out of bounds FIRST
					res[i] = nums[left];
					left--;
				} else {
					res[i] = nums[right];
					right++;
				}
			}
		}
		return res;
	}
}
