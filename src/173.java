import java.util.Stack;

class BinarySearchTreeIterator {
	class BSTIterator {

		private Stack<TreeNode> stack = new Stack<>();

		public BSTIterator(TreeNode root) {
			//initialize stack with everything to the left
			//then when we call next, pop from the stack and add the left traversal of the right node
			pushLeftBranch(root);
		}

		private void pushLeftBranch(TreeNode root) {
			TreeNode curr = root;
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
		}

		/** @return the next smallest number */
		public int next() {
			TreeNode next = stack.pop();
			if (next.right != null) {
				pushLeftBranch(next.right);
			}
			return next.val;
		}

		/** @return whether we have a next smallest number */
		public boolean hasNext() {
			return !stack.isEmpty();
		}
	}

}
