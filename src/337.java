class HouseRobberIII {

	public int rob(TreeNode root) {

		if (root == null) return 0;
		if (root.left == null && root.right == null) return root.val;

		int skipThis = rob(root.left) + rob(root.right);
		int dontSkipThis = root.val
				+ (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
				+ (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

		return Math.max(skipThis, dontSkipThis);
	}






	// ACTUALLY NOT DP IF YOU THINK ABOUT IT, NO ACTUAL REUSE GOING ON
	// SLOWER THAN WITHOUT THE MAP
	/*public int rob(TreeNode root) {
		Map<TreeNode, Integer> dp = new HashMap<>();
		return rob(root, dp);
	}

	private int rob(TreeNode root, Map<TreeNode, Integer> dp) {
		if (root == null) return 0;
		if (root.left == null && root.right == null) return root.val;
		if (dp.containsKey(root)) return dp.get(root);

		int skipThis = rob(root.left) + rob(root.right);
		int dontSkipThis = root.val
				+ (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right))
				+ (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

		int maxRobbed = Math.max(skipThis, dontSkipThis);
		dp.put(root, maxRobbed);
		return maxRobbed;
	}*/
}
