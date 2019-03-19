class ConvertBinarySearchTreeToSortedDoublyLinkedList {

	private static Node prev;

	public Node treeToDoublyList(Node root) {
		if (root == null) return null;

		//simply do an in-order traversal
		//prev.right = root
		//root.left = prev;
		//first node will have dummy.right = first and first.left = dummy
		//last node will not connect to dummy

		//then we need to, in the end, update prev and connect it with dummy.right
		Node dummy = new Node(0, null, null);
		prev = dummy;
		traverse(root);

		prev.right = dummy.right;
		dummy.right.left = prev;
		return dummy.right;
	}

	private void traverse(Node root) {
		if (root == null) return;

		traverse(root.left);

		prev.right = root;
		root.left = prev;
		prev = root;

		traverse(root.right);
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
