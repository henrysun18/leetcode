import java.util.HashMap;
import java.util.Map;

class ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder == null || preorder.length == 0) {
			return null;
		}

		TreeNode[] treeNodes = new TreeNode[preorder.length];
		for (int i = 0; i < preorder.length; i++) {
			treeNodes[i] = new TreeNode(preorder[i]);
		}

		boolean processingPreorder = true;
		int pre = 1;
		int in = 0;

		Map<Integer, TreeNode> seen = new HashMap<>();

		while (pre < preorder.length && in < inorder.length) {
			TreeNode curr = processingPreorder ? treeNodes[pre-1] : seen.get(inorder[in-1]);

			if (processingPreorder) {
				if (curr.left == null) {
					curr.left = treeNodes[pre]; //go left until we see they're equal
				} else {
					curr.right = treeNodes[pre];
				}

				seen.put(preorder[pre], treeNodes[pre]);
				if (preorder[pre] == inorder[in]) {
					processingPreorder = false;
				}

				pre++;
			} else {
				if (!seen.containsKey(inorder[in])) {
					processingPreorder = true;
				}

				in++;
			}
		}

		return treeNodes[0]; //first element of preorder traversal is always the root
	}
}
