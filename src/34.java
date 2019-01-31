//problem 34
class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
		//first run binary search to find target, just like normal
		//then run binary search starting from there going in both directions to find the edges
		int left = 0;
		int right = nums.length - 1;
		int middle = right / 2;
		int start = -1, end = -1;
		while (left <= right) {
			if (nums[middle] == target) {
				//start running binary search in both directions to find edges
				start = searchStart(nums, left, middle, target);
				end = searchEnd(nums, middle, right, target);
				break;
			} else if (target < nums[middle]) {
				right = middle-1;
				middle = (left + right) / 2;
			} else {
				left = middle+1;
				middle = (left + right) / 2;
			}
		}

		return new int[] {start, end};
	}

	private int searchStart(int[] nums, int left, int right, int target) {
		if (left > right) return -1;

		int middle = (left + right) / 2;
		if (nums[middle] == target) {
			if (middle == 0 || nums[middle-1] < target) {
				return middle;
			} else {
				return searchStart(nums, left, middle-1, target);
			}
		}

		return searchStart(nums, middle+1, right, target);
	}

	private int searchEnd(int[] nums, int left, int right, int target) {
		if (left > right) return -1;

		int middle = (left + right) / 2;
		if (nums[middle] == target) {
			if (middle == nums.length-1 || nums[middle+1] > target) {
				return middle;
			} else {
				return searchEnd(nums, middle+1, right, target);
			}
		}

		return searchEnd(nums, left, middle-1, target);
	}
}
