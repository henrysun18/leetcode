import java.util.Arrays;

class LongestIncreasingSubsequence {
	public int lengthOfLIS(int[] nums) {
		int dp[] = new int[nums.length];

		//dp[0] stores the index of the smallest num seen so far, that completes a 1-element subsequence
		//dp[5] stores the index of the smallest num seen so far, that completes a 6-element subsequence
		//for each num, binary search through dp to see which subsequence it can improve
		//since subsequences are increasing, it's expected that dp[] elements are increasing / sorted
		//thus, we can binary search in logn time to find the first value greater than num

		//init dp[] by setting all elements to max int
		for (int i = 0; i < nums.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		int len = 0;
		for (int num : nums) {
			//binarySearch returns insertion point as a negative value so we can infer it couldn't find the exact value in the array!
			int bs = Arrays.binarySearch(dp, num);
			if (bs >= 0) continue; //num is already found in nums array

			int insertionPoint = -(bs + 1);

			dp[insertionPoint] = num;
			if (insertionPoint+1 > len) {
				len = insertionPoint + 1;
			}
		}

		return len;
	}

	public static void main(String[] args) {
		LongestIncreasingSubsequence sol = new LongestIncreasingSubsequence();
		sol.lengthOfLIS(new int[]{3,5,6,2,5,4,19,5,6,7,12});
	}
}
