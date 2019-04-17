class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
		//if root < 0, check if we can get a max from the left subtree
		//basically, we want to see if it's worth including root+ some optimal path in the left tree with the optimal path on the right
		//or, we can exclude root and just find maxPathSum(root.left) vs maxPathSum(root.right)

		//if root > 0, then we either include it and find the optimal left&right branches,
		//or we don't include and choose either maxPathSum(left) or maxPathSum(right)
		if (root == null) return 0;
		if (root.left == null && root.right == null) return root.val;

		int leftMaxPathSum = maxPathSum(root.left);
		int leftMaxBranchSum = maxBranchSum(root.left);
		int rightMaxPathSum = maxPathSum(root.right);
		int rightMaxBranchSum = maxBranchSum(root.right);

		int max = root.val;
		if (root.left != null) {
			//check leftMaxPathSum, leftMaxBranchSum + root.val
			max = Math.max(max, Math.max(leftMaxPathSum, leftMaxBranchSum + root.val));
		}
		if (root.right != null) {
			max = Math.max(max, Math.max(rightMaxPathSum, rightMaxBranchSum + root.val));
		}
		if (root.left != null && root.right != null) {
			max = Math.max(max, leftMaxBranchSum + root.val + rightMaxBranchSum);
		}
		return max;
	}

	private int maxBranchSum(TreeNode root) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return root.val;

		int maxLeft = maxBranchSum(root.left);
		int maxRight = maxBranchSum(root.right);

		//include maxLeft or include maxRight ?
		return Math.max(root.val, Math.max(root.val + maxLeft, root.val + maxRight));
	}
}
