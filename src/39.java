import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> combinations = new LinkedList<>();

		if (target == 0) {
			//the only case where we would get duplicate combinations
			combinations.add(new ArrayList<>());
			return combinations;
		}

		//for all candidates, try adding it then apply the same thing for the i to nth candidates
		//for all candidates, also try not adding it then apply the same thing for the i+1 to nth candidates
		List<Integer> sol = new ArrayList<>();

		helper(candidates, target, 0, sol, combinations);

		return combinations;
	}

	private void helper(int[] candidates, int target, int currIndex, List<Integer> sol, List<List<Integer>> combinations) {
		if (target == 0) {
			combinations.add(new ArrayList<>(sol));
			return; //forgot to put return statement here, and accidentally dfsed too deep causing duplicates
		}
		if (target < 0 || currIndex >= candidates.length) {
			return;
		}

		//initially put a for loop here from currIndex to length, which is unneeded since we're already handling it with the "moving on" part

		//try to include, then don't move on yet since we might want to repeat
		sol.add(candidates[currIndex]);
		helper(candidates, target-candidates[currIndex], currIndex, sol, combinations);
		sol.remove(sol.size()-1); //backtrack

		//don't include and move on
		helper(candidates, target, currIndex+1, sol, combinations);
	}
}
