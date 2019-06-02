class DiameterOfBinaryTree {
	int max = 0; //can just pass around an int[1] if we don't want global vars

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) return 0;

		maxDepth(root);

		return max;
	}

	private int maxDepth(TreeNode root) {
		if (root == null) return -1;

		int leftDepth = 1 + maxDepth(root.left);
		int rightDepth = 1 + maxDepth(root.right);

		int diameter = leftDepth + rightDepth;
		if (diameter > max) {
			max = diameter;
		}

		return Math.max(leftDepth, rightDepth);
	}
}
