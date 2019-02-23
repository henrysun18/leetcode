class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		TreeNode root = new TreeNode(nums[nums.length/2]);
		root.left = getTree(nums, 0, nums.length/2 - 1);
		root.right = getTree(nums, nums.length/2 + 1, nums.length-1);

		return root;
	}

	private TreeNode getTree(int[] nums, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = (start + end) / 2;

		TreeNode root = new TreeNode(nums[mid]);
		root.left = getTree(nums, start, mid-1);
		root.right = getTree(nums, mid+1, end);

		return root;
	}
}
