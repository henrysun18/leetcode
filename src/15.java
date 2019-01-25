import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Henry on 1/22/2019.
 */
class ThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new LinkedList<>();

		Arrays.sort(nums);
		for (int i = 0; i < nums.length - 2; i++) {
			int a = nums[i];
			if (i > 0 && a == nums[i-1]) {
				continue; //ignore duplicates by only picking unique values for a
			}

			int left = i + 1;
			int right = nums.length - 1;

			while (left < right) {
				int b = nums[left];
				int c = nums[right];
				int sum = a + b + c;
				if (sum == 0) {
					result.add(Arrays.asList(a, b, c));

					while (left < right && b == nums[left]) {
						left++;
					}
					while (left < right && c == nums[right]) {
						right--;
					}
				} else if (sum < 0) {
					while (left < right && b == nums[left]) {
						left++;
					}
				} else {
					while (left < right && c == nums[right]) {
						right--;
					}
				}
			}
		}

		return result;
	}
}
