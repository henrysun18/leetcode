import java.util.HashMap;
import java.util.Map;

class SubarraySumEqualsK {
	public int subarraySum(int[] nums, int k) {
		//if we want to test every possible subarray, then there are n^2 subarrays to test
		//(starting index from 0 to length-1, end index from 1 to length)
		//but finding the sum would be easy, just take the running sum from start to end to get all sums for one start index

		//can we get time complexity to O(n) using a hashmap or something?
		//maybe dp?
		//we want to know how many in subarray starting from index 1 sum to k-nums[0] + how many in subarray sum to k

		//i think i get it now, after seeing the solution
		//start from left and go right while forming a running sum
		//with each running sum, increment hashmap of sum -> occurrences
		//if we're adding a num and runningsum becomes k, then we know that we can get k if we include everything starting from 0
		//then we also check if removing any portion of the beginning part of the array can also give us k
		//so we want to find a number such that removed+k = sum
		//so we want to check occurrences of sum - k

		Map<Integer, Integer> sumOccurrences = new HashMap<>();

		int sums = 0;
		int runningSum = 0;
		for (int num : nums) {
			runningSum += num;
			if (runningSum == k) {
				sums++;
			}
			sums += sumOccurrences.getOrDefault(runningSum-k, 0);

			sumOccurrences.put(runningSum, sumOccurrences.getOrDefault(runningSum, 0) + 1);
		}

		return sums;
	}
}
