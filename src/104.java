class MaximumDepthOfBinaryTree {
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		} else if (root.left == null && root.right == null) {
			return 1;
		} else {
			return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		}
	}
}
