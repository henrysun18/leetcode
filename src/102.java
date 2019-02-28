import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class BinaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrderRecursive(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}
		res.add(new ArrayList<>());
		res.get(0).add(root.val);

		addChildren(root.left, 1, res);
		addChildren(root.right, 1, res);

		return res;
	}

	private void addChildren(TreeNode node, int level, List<List<Integer>> res) {
		if (node == null) {
			return;
		}
		if (res.size() <= level) {
			res.add(new ArrayList<>());
		}
		res.get(level).add(node.val);

		addChildren(node.left, level+1, res);
		addChildren(node.right, level+1, res);
	}








	public List<List<Integer>> levelOrderIterative(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();

		if (root == null) {
			return res;
		}
		Map<TreeNode, Integer> nodeLevels = new HashMap<>();
		LinkedList<TreeNode> queue = new LinkedList<>();

		queue.add(root);
		nodeLevels.put(root, 0);

		while (!queue.isEmpty()) {
			TreeNode node = queue.removeFirst(); //pop?
			int level = nodeLevels.get(node);
			if (res.size() == level) {
				res.add(new ArrayList<>());
			}
			res.get(level).add(node.val);

			TreeNode left = node.left;
			TreeNode right = node.right;
			if (left != null) {
				nodeLevels.put(left, level+1);
				queue.add(left);
			}
			if (right != null) {
				nodeLevels.put(right, level+1);
				queue.add(right);
			}
		}

		return res;
	}
}
