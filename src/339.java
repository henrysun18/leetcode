import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // Constructor initializes an empty nested list.
    //public NestedInteger();
    // Constructor initializes a single integer.
    //public NestedInteger(int value);
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger();
    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();
    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value);
    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni);
    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
}



class NestedListWeightSum {
	public int depthSumBfs(List<NestedInteger> nestedList) {
		if (nestedList == null) return 0;

		Queue<NestedInteger> queue = new LinkedList<>(nestedList);
		int level = 1;
		int sum = 0;
		while (!queue.isEmpty()) {
			int numInLevel = queue.size();
			for (int i = 0; i < numInLevel; i++) {
				NestedInteger n = queue.poll();
				if (n.isInteger()) {
					sum += n.getInteger() * level;
				} else {
					queue.addAll(n.getList());
				}
			}
			level++;
		}
		return sum;
	}



	public int depthSumDfs1(List<NestedInteger> nestedList) {
		if (nestedList == null) return 0;

		return depthSumDfs2(nestedList, 1);
	}

	private int depthSumDfs2(List<NestedInteger> nestedList, int level) {
		int sum = 0;

		for (NestedInteger n : nestedList) {
			if (n.isInteger()) {
				sum += level * n.getInteger();
			} else {
				sum += depthSumDfs2(n.getList(), level+1);
			}
		}

		return sum;
	}
}
