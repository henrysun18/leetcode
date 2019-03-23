import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class SerializeAndDeserializeNaryTree {
	 // Encodes a tree to a single string.
	 public String serialize(Node root) {
		 // wrap nodes with []
		 // if node has children, wrap all children with []
		 if (root == null) {
			 return "";
		 }

		 StringBuilder sb = new StringBuilder();
		 sb.append(root.val);

		 if (root.children != null && !root.children.isEmpty()) {
			 sb.append('[');

			 List<String> children = new LinkedList<>();
			 for (Node child : root.children) {
				 children.add(serialize(child));
			 }
			 sb.append(String.join(",", children));

			 sb.append(']');
		 }

		 System.out.println(sb.toString());
		 return sb.toString();
	 }

	 // Decodes your encoded data to tree.
	 public Node deserialize(String data) {
		 if (data == null || data.equals("[]")) {
			 return null;
		 }
		 // go through each char in string, if see [, push curr to stack and go to its children,
		 // if see ], pop from stack to go back to parent
		 LinkedList<Node> path = new LinkedList<>();
		 Node curr = null;

		 StringBuilder sb = new StringBuilder();
		 List<Node> children = new ArrayList<>();

		 for (char c : data.toCharArray()) {
			 if (c >= '0' && c <= '9') {
				 sb.append(c);
			 } else {
				 if (c == '[') { //start of children list
					 int num = Integer.parseInt(sb.toString());
					 sb.setLength(0);
					 curr = new Node(num, new ArrayList<>());
					 path.push(curr);
				 } else if (c == ']') {
					 Node parent = path.pop();
					 if (sb.length() > 0) {
						 addRunningNumToChildren(sb, parent.children);
					 } else {
						 parent.children.add(curr);
					 }
					 curr = parent;
					 //path.push(parent);
					 //curr.children = new ArrayList<>(children); //create a copy of the list, otherwise clear() in the next line destroys it lol
					 //children.clear();
				 } else if (c == ',') {
					 Node parent = path.pop();
					 if (sb.length() > 0) {
						 // only add running num if it's purely a num and not a node with children
						 addRunningNumToChildren(sb, parent.children);
					 } else {
						 // if not running num, then we need to add the NODE to children, otherwise it will get ignored
						 parent.children.add(curr);
					 }
					 path.push(parent);
				 }
			 }
		 }

		 // deal with case where only number is present
		 if (sb.length() > 0) {
			 return new Node(Integer.parseInt(sb.toString()), new ArrayList<>());
		 }

		 return curr;
	 }

	 private void addRunningNumToChildren(StringBuilder sb, List<Node> children) {
		 int num = Integer.parseInt(sb.toString());
		 sb.setLength(0);//clear stringbuilder

		 Node curr = new Node(num, new ArrayList<>()); //initialize children instead of keeping it at null
		 children.add(curr);
	 }





	class Node {
		public int val;
		public List<Node> children;

		public Node() {}

		public Node(int _val,List<Node> _children) {
			val = _val;
			children = _children;
		}
	};
}
