import java.util.ArrayList;
import java.util.List;

class Subsets {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		getSubsets(nums, res, tmp, 0);

		return res;
	}

	private void getSubsets(int[] nums, List<List<Integer>> res, List<Integer> tmp, int curr) {
		if (curr == nums.length) {
			res.add(new ArrayList<Integer>(tmp));
			return;
		}

		//don't include element
		getSubsets(nums, res, tmp, curr+1);

		//include element
		tmp.add(nums[curr]);
		getSubsets(nums, res, tmp, curr+1);
		tmp.remove(tmp.size()-1);
	}
}
