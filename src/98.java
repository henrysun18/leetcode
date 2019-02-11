class ValidateBinarySearchTree {
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isValidNode(root.left, null, root.val) && isValidNode(root.right, root.val, null);
	}

	private boolean isValidNode(TreeNode root, Integer min, Integer max) {
		if (root == null) {
			return true;
		}
		if (max != null && root.val >= max || min != null && root.val <= min) {
			return false;
		}
		return isValidNode(root.left, min, root.val) && isValidNode(root.right, root.val, max);
	}
}
