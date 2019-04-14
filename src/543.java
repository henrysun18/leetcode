class DiameterOfBinaryTree {
	public int diameterOfBinaryTree(TreeNode root) {
		//want the max depth of left and max depth of right
		if (root == null) return 0;

		int[] maxDiameter = new int[1];
		int leftDepth = 1 + depth(root.left, maxDiameter);
		int rightDepth = 1 + depth(root.right, maxDiameter);


		return Math.max(maxDiameter[0], leftDepth + rightDepth);
	}

	private int depth(TreeNode node, int[] maxDiameter) {
		if (node == null) return -1;
		if (node.left == null && node.right == null) return 0;

		int leftDepth = 1 + depth(node.left, maxDiameter);
		int rightDepth = 1 + depth(node.right, maxDiameter);

		if (leftDepth + rightDepth > maxDiameter[0]) {
			maxDiameter[0] = leftDepth + rightDepth;
		}
		return Math.max(leftDepth, rightDepth);
	}
}
