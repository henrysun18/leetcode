import java.util.LinkedList;
import java.util.List;

class ConvertBinarySearchTreeToSortedDoublyLinkedList {

	public Node treeToDoublyList(Node root) {
		if (root == null) return null;

		List<Node> traversal = new LinkedList<>();
		traverse(root, traversal);

		for (int i = 0; i < traversal.size(); i++) {
			int left = (i+traversal.size()-1) % traversal.size();
			int right = (i+1) % traversal.size();
			traversal.get(i).left = traversal.get(left);
			traversal.get(left).right = traversal.get(i);
		}

		return traversal.get(0);
	}

	private void traverse(Node root, List<Node> traversal) {
		if (root.left != null) {
			traverse(root.left, traversal);
		}
		traversal.add(root);
		if (root.right != null) {
			traverse(root.right, traversal);
		}
	}

	class Node {
		public int val;
		public Node left;
		public Node right;

		public Node() {}

		public Node(int _val,Node _left,Node _right) {
			val = _val;
			left = _left;
			right = _right;
		}
	};
}
