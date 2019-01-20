import java.util.HashMap;
import java.util.Map;

/**
 * Created by Henry on 5/14/2018.
 * Problem 1
 */
class TwoSum {
	public int[] twoSum(int[] nums, int target) {

		Map pastNums = new HashMap<Integer, Integer>(); //key: num; value: index
		int[] sol = new int[2];

		for (int i = 0; i < nums.length; i++) {
			Integer currentNum = nums[i];
			Integer complimentIndex = (Integer) pastNums.get(target - currentNum);
			if (complimentIndex != null) { //found compliment
				sol[1] = complimentIndex;
				sol[2] = i;
			} else { //add current value to map
				pastNums.put(currentNum, i);
			}
		}

		return sol;
	}
}
