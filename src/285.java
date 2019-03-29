import java.util.Stack;

class InorderSuccessorInBST {

	public TreeNode inorderSuccessorRecursive(TreeNode root, TreeNode p) {
		if (root == null) return null;
		if (root.val == p.val) {
			return leftmost(root.right);
		} else if (p.val > root.val) {
			return inorderSuccessorRecursive(root.right, p);
		} else {
			TreeNode successor = inorderSuccessorRecursive(root.left, p);
			return successor == null ? root : successor;
		}

	}
	private TreeNode leftmost(TreeNode root) {
		if (root == null) return null;
		if (root.left == null) return root;
		return leftmost(root.left);
	}


	public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
		// literally just do a backwards inorder traversal and return prev once we find p
		// actually no, cuz that's worst case O(n), but we have a BST
		// so let's just find the node first, then find the key greater than it
		// if there's nothing to the right, we need to return the parent
		// otherwise just return the leftmost child to the right

		// we should remember the path we took to get to p
		// when we find p, either return leftmost child of p.right,
		// or backtrack

		Stack<TreeNode> path = new Stack<>();
		TreeNode curr = root;
		while (curr != null) {
			if (curr.val == p.val) {
				//try returning leftmost child of curr.right
				if (curr.right != null) {
					curr = curr.right;
					while (curr.left != null) {
						curr = curr.left;
					}
					return curr;
				} else {
					//backtrack and try to find a greater parent. if none found, we can only assume p is far right in the tree
					while (!path.isEmpty()) {
						curr = path.pop();
						if (curr.val > p.val) {
							return curr;
						}
					}
					return null;
				}
			}
			path.push(curr);
			if (p.val < curr.val) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
		return null; //p was never found
	}
}
