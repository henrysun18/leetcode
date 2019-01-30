//problem 33
class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		// binary search starting from middle, so we need helper function specifying index
		if (nums.length == 0) {
			return -1;
		}
		return search(nums, 0, nums.length-1, target);
	}

	private int searchFirstTryBugged(int[] nums, int start, int end, int target) {
		if (start > end) {
			return -1;
		}

		int middle = (start + end) / 2;
		if (nums[middle] == target) {
			return middle;
		}

		// if left half looks abnormal, we assume right half is increasing
		if (nums[start] > nums[middle]) {
			if (target > nums[middle]) {
				return search(nums, middle+1, end, target);
			}
		}

		//if right half looks abnormal, we assume left half is increasing
		if (nums[end] < nums[middle]) {
			if (target < nums[middle]) {
				return search(nums, start, middle-1, target);
			}
		}

		// both halves look normal, pivot could be on any side
		int leftResult = search(nums, start, middle-1, target);
		if (leftResult >= 0) {
			return leftResult;
		}
		int rightResult = search(nums, middle+1, end, target);
		if (rightResult >= 0) {
			return rightResult;
		}

		return -1;
		//could just go straight to returning search(nums, middle+1, end, target)
		//but this is more readable
	}


	private int search(int[] nums, int start, int end, int target) {
		if (start > end) {
			return -1;
		}

		int middle = (start + end) / 2;
		if (nums[middle] == target) {
			return middle;
		}

		// if left half looks abnormal, we assume right half is increasing
		if (nums[start] > nums[middle]) {
			if (target > nums[middle] && target <= nums[end]) {
				return search(nums, middle+1, end, target);
			}
		}

		//if right half looks abnormal, we assume left half is increasing
		if (nums[end] < nums[middle]) {
			if (target >= nums[start] && target < nums[middle]) {
				return search(nums, start, middle-1, target);
			}
		}

		// both halves look normal, pivot could be on any side
		int leftResult = search(nums, start, middle-1, target);
		int rightResult = search(nums, middle+1, end, target);
		return Math.max(leftResult, rightResult);
	}
}
