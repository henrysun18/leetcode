import java.util.HashMap;
import java.util.Map;

class MaximumSizeSubarraySumEqualsK {
	public int maxSubArrayLen(int[] nums, int k) {
		//brute force is just try all subarrays which is n^2
		//can we do this in one pass? usually array questions are either binary search lgn or one or a few passes

		//two pointers, start with pointers at edges and total sum by doign a first pass
		//auxiliary hashmap
		//running sum starting from beginning,
		//map running sum to integer which denotes the minimum number of nums starting from index 0 needed to reach this sum
		//start with full sum and pointer at end
		//move pointer inwards while looking for minimum

		//hashmap so blessed, helped me to think of an O(n) solution; reduced time complexity by a long shot as long as i knew how to leverage this data structure
		Map<Integer, Integer> sumToMinNums = new HashMap<>();
		int runningSum = 0;
		for (int i = 0; i < nums.length; i++) {
			int num = nums[i];
			runningSum += num;

			if (sumToMinNums.get(runningSum) == null) {
				sumToMinNums.put(runningSum, i+1);
			}//if we find the same runningSum in map already, means we can get the sum with less amount of nums used, so just ignore the runningSum in this case
		}

		//start with full sum and start from end of nums array
		int maxSubArrayLen = 0;
		for (int i = nums.length-1; i >= 0; i--) {
			if (runningSum == k) {
				//deal with case where we don't need to remove anything from beginning of array!! don't break and return early pls
				maxSubArrayLen = Math.max(maxSubArrayLen, i+1);
			}

			Integer numsToRemoveToGetValidSubArray = sumToMinNums.get(runningSum - k);

			if (numsToRemoveToGetValidSubArray != null) {
				maxSubArrayLen = Math.max(maxSubArrayLen, i+1 - numsToRemoveToGetValidSubArray); //also handles the case when i+1 - nums... is negative
			}

			//decrement runningSum, dont forget
			runningSum -= nums[i];
		}

		return maxSubArrayLen;
	}
}
