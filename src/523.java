import java.util.HashMap;
import java.util.Map;

class ContinuousSubarraySum {
	public boolean checkSubarraySum(int[] nums, int k) {

		if (nums == null || nums.length < 2) return false;

		Map<Integer, Integer> remainders = new HashMap<>();

		//pls deal with k = 0 case. NOTE THAT THE QUESTION SAID "NON-NEGATIVE", SO WE SHOULD CHECK 0!!!
		//actually, if there's consecutive 0's then any k will satisfy, and we should return true
		// also pls check the case where k < 0, since the question said k can be an "INTEGER"
		k = Math.abs(k);

		int modSum = nums[0];
		remainders.put(nums[0], 0);

		for (int i = 1; i < nums.length; i++) {
			if (k == 0) {
				if (nums[i-1] == 0 && nums[i] == 0) {
					return true;
				}
			} else {
				modSum += nums[i];
				modSum = modSum%k;

				if (modSum == 0) return true;

				//can we subtract a continuous portion of the array starting at index 0 up to i-1 (leaving 2+ elements)
				//such that the new sum is divisible by k?
				if (remainders.containsKey(modSum) && remainders.get(modSum) < i-1) return true;

				remainders.putIfAbsent(modSum, i); //take the smaller i
			}
		}

		return false;
	}
}
