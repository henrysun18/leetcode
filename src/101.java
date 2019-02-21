import java.util.ArrayList;
import java.util.List;

class SymmetricTree {
	public static void main(String[] args) {
		SymmetricTree app = new SymmetricTree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(3);
		root.right.left = new TreeNode(2);
		app.isSymmetric(root);
	}

	public boolean isSymmetric(TreeNode root) {
		// traversal of left side should be opposite of the traversal of right side
		// include nulls as well
		List<TreeNode> leftWalk = new ArrayList<>();
		List<TreeNode> rightWalk = new ArrayList<>();

		if (root == null) {
			return true;
		}
		walkLeft(root.left, leftWalk);
		walkRight(root.right, rightWalk);

		if (leftWalk.size() != rightWalk.size()) {
			return false;
		}
		for (int i = 0; i < leftWalk.size(); i++) {
			if (leftWalk.get(i) == null && rightWalk.get(i) != null || leftWalk.get(i) != null && rightWalk.get(i) == null) {
				return false;
			}
			if (leftWalk.get(i) == rightWalk.get(i)) continue;
			if (leftWalk.get(i).val != rightWalk.get(i).val) {
				return false;
			}
		}
		return true;
	}

	private void walkLeft(TreeNode root, List<TreeNode> path) {
		if (root != null) {
			walkLeft(root.left, path);
			path.add(root);
			walkLeft(root.right, path);
		} else {
			path.add(root);
		}
	}
	private void walkRight(TreeNode root, List<TreeNode> path) {
		if (root != null) {
			walkRight(root.right, path);
			path.add(root);
			walkRight(root.left, path);
		} else {
			path.add(root);
		}
	}
}
