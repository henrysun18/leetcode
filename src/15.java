import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Henry on 1/22/2019.
 */
class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> sol = new ArrayList<>();

		//if we sort nums then we can try all candidate triplets in n^2 time
		Arrays.sort(nums);
		for (int i = 0; i < nums.length-2; i++) {
			if (i > 0 && nums[i] == nums[i-1]) continue;

			int l = i+1;
			int r = nums.length-1;
			while (l < r) {
				int sum = nums[l] + nums[r] + nums[i];
				if (sum == 0) {
					sol.add(Arrays.asList(nums[i], nums[l], nums[r]));

					//increment l until we find new value; decrement r until we find new value
					while (l+1 < nums.length && nums[l] == nums[l+1]) {
						l++;
					}
					while (r-1 >= 0 && nums[r-1] == nums[r]) {
						r--;
					}
					l++;
					r--;
				} else if (sum < 0) {
					l++; //don't need to put a while loop here since the parent while loop handles it anyway
				} else {
					r--;
				}
			}
		}

		return sol;
	}
}
