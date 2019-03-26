import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class NestedListWeightSumII {
	public int depthSumInverseBFS(List<NestedInteger> nestedList) {
		// we can dfs to bottom to find depth, then do another pass to get the sum
		//or we can bfs and tally the sums for each level

		List<Integer> sums = new ArrayList<>();
		Queue<NestedInteger> queue = new LinkedList<>(nestedList);
		int levelIndex = 0;

		while (!queue.isEmpty()) {
			int numInLevel = queue.size();
			sums.add(0); //create new level!
			for (int i = 0; i < numInLevel; i++) {
				NestedInteger n = queue.poll();
				if (n.isInteger()) {
					sums.set(levelIndex, sums.get(levelIndex) + n.getInteger());
				} else {
					queue.addAll(n.getList());
				}
			}
			levelIndex++;
		}

		int currLevel = sums.size();
		int sum = 0;
		for (int i : sums) {
			sum += i * currLevel;
			currLevel--;
		}
		return sum;
	}



	public int depthSumInverseBFSNoMultiplication(List<NestedInteger> nestedList) {
		// we can do it without depth variable and without multiplication
		int sum = 0;
		Queue<NestedInteger> queue = new LinkedList<>(nestedList);
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean lastLevel = true;
			for (int i = 0; i < size; i++) {
				NestedInteger n = queue.poll();
				if (n.isInteger()) {
					sum += n.getInteger();
					queue.add(n); //add it all over again until no more nested lists
				} else {
					queue.addAll(n.getList());
					lastLevel = false;
				}
			}
			if (lastLevel) break;
		}
		return sum;
	}
}
