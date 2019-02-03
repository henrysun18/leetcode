import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Permutations {
	public List<List<Integer>> permute(int[] nums) {

		List<List<Integer>> permutations = new LinkedList<>();
		List<Integer> tmp = new ArrayList<>();

		permute(nums, tmp, permutations);
		return permutations;
	}

	private void permute(int[] nums, List<Integer> tmp, List<List<Integer>> permutations) {
		if (tmp.size() == nums.length) {
			permutations.add(new ArrayList<>(tmp));
		}

		// try to add all nums to tmp
		for (int i = 0; i < nums.length; i++) {
			if (!tmp.contains(nums[i])) {
				tmp.add(nums[i]);
				permute(nums, tmp, permutations);
				tmp.remove(tmp.size() - 1);
			}
		}
	}
}
