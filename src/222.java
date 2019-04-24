class CountCompleteTreeNodes {
	private int rightHeight = 0;
	private int missingLeaves = 0;
	private boolean done = false;

	public int countNodes(TreeNode root) {
		//assume that we're GIVEN a complete binary tree.
		//are there any cases where we can't simply count the total number of nodes in the tree?
		//root is complete iff left is complete && right is complete
		//literally just bfs and count the total number of nodes?

		//ok i kinda get the catch now
		//it's complete, so we just need to do a backwards inorder traversal to break early. i.e. don't need to always run in O(n)
		//for the example case we'll know as soon as we see 1-3-null-6-null in the backwards traversal that the solution is 2*(height+1)-1 - missing(which is 1)
		//worst case is O(n) still tho
		//but the best case is O(h), i.e. O(lgn)

		//so we can do a simple backwards inorder traversal using dfs (recursion), taking note the number of missing leaves.
		//count the missing leaves until we see a leaf, then break early
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;

		TreeNode curr = root;
		while (curr.right != null) {
			curr = curr.right;
			rightHeight++;
		}

		dfs(root, 0);

		return (1 << rightHeight+2)-1 - missingLeaves; //i.e. 2^(rightHeight+1) - 1 + the leaves
	}

	private void dfs(TreeNode node, int height) {
		if (done || node == null) return;

		if (node.right == null) {
			//increment missingLeaves if needed now
			//note that with this flow, a perfect tree will not have missingLeaves
			if (height == rightHeight) {
				missingLeaves++;
			} else {
				done = true;
			}
		}
		if (node.left == null) {
			if (height == rightHeight) {
				missingLeaves++;
			}
		}

		dfs(node.right, height+1);
		dfs(node.left, height+1);
	}





	public int countNodesDFSNaive(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return 1;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}
}
