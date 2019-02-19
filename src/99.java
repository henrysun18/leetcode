class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class RecoverBinarySearchTree {
	public void recoverTree(TreeNode root) {
		if (root == null) {
			return;
		}

		if (root.left != null) {
			TreeNode rightMostChildOfLeftSubtree = rightMostChildOfLeftSubtree(root.left);
			if (rightMostChildOfLeftSubtree.val > root.val) {
				swap(root, rightMostChildOfLeftSubtree);
			}
		}
		if (root.right != null) {
			TreeNode leftMostChildOfRightSubtree = leftMostChildOfRightSubtree(root.right);
			if (leftMostChildOfRightSubtree.val < root.val) {
				swap(root, leftMostChildOfRightSubtree);
			}
		}

		return;
	}

	private TreeNode rightMostChildOfLeftSubtree(TreeNode root) {
		if (root == null || root.left == null && root.right == null) {
			return root;
		}

		TreeNode rightMostChildOfLeftSubtree = rightMostChildOfLeftSubtree(root.left);
		if (rightMostChildOfLeftSubtree != null && rightMostChildOfLeftSubtree.val > root.val) {
			swap(root, rightMostChildOfLeftSubtree);
		}

		TreeNode leftMostChildOfRightSubtree = leftMostChildOfRightSubtree(root.right);
		if (leftMostChildOfRightSubtree != null && leftMostChildOfRightSubtree.val < root.val) {
			swap(root, leftMostChildOfRightSubtree);
		}

		//now return what caller is asking for
		if (root.left == null) {
			return root;
		} else {
			return rightMostChildOfLeftSubtree(root.left);
		}
	}

	private TreeNode leftMostChildOfRightSubtree(TreeNode root) {
		if (root.left == null && root.right == null) {
			return root;
		}

		TreeNode rightMostChildOfLeftSubtree = rightMostChildOfLeftSubtree(root.left);
		if (rightMostChildOfLeftSubtree != null && rightMostChildOfLeftSubtree.val > root.val) {
			swap(root, rightMostChildOfLeftSubtree);
		}

		TreeNode leftMostChildOfRightSubtree = leftMostChildOfRightSubtree(root.right);
		if (leftMostChildOfRightSubtree != null && leftMostChildOfRightSubtree.val < root.val) {
			swap(root, leftMostChildOfRightSubtree);
		}

		//now return what caller is asking for
		if (root.right == null) {
			return root;
		} else {
			return leftMostChildOfRightSubtree(root.right);
		}
	}

	private void swap(TreeNode a, TreeNode b) {
		int tmp = a.val;
		a.val = b.val;
		b.val = tmp;
	}
}
