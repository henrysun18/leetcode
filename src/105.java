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
		boolean currAtPre = true;
		int pre = 0;
		int in = 0;

		Map<Integer, TreeNode> seen = new HashMap<>();
		seen.put(preorder[0], treeNodes[0]);

		while (pre < preorder.length && in < inorder.length) {
			TreeNode curr = currAtPre ? treeNodes[pre] : seen.get(inorder[in-1]);

			if (processingPreorder) {
				seen.put(preorder[pre], treeNodes[pre]);

				if (preorder[pre] == inorder[in]) {
					processingPreorder = false;
					in++;
					continue;
				}

				if (!currAtPre) {
					curr.right = treeNodes[pre+1];
					currAtPre = true;
				} else {
					curr.left = treeNodes[pre+1];
				}

				pre++;
			} else {
				if (seen.containsKey(inorder[in])) {
					in++;
				} else {
					processingPreorder = true;
					currAtPre = false;
				}
			}
		}

		return treeNodes[0]; //first element of preorder traversal is always the root
	}
}
