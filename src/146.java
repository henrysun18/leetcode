import java.util.HashMap;
import java.util.Map;

class LRUCache {

	private Map<Integer, Node> keyNodeMap = new HashMap<>();
	private Node oldestNode = null;
	private Node newestNode = null;
	private final int capacity;

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (!keyNodeMap.containsKey(key)) {
			return -1;
		}

		Node node = keyNodeMap.get(key);
		setNewest(node);

		return node.val;
	}

	public void put(int key, int value) {
		if (!keyNodeMap.containsKey(key)) {
			Node newNode = new Node(key, value);
			keyNodeMap.put(key, newNode);

			if (oldestNode == null) {
				oldestNode = newNode;
			}
			if (newestNode != null) {
				newestNode.next = newNode;
				newNode.prev = newestNode;
			}
			newestNode = newNode;

			removeOldestIfNeeded();
		} else {
			Node existingNode = keyNodeMap.get(key);
			existingNode.val = value;

			setNewest(existingNode);
		}
	}

	private void removeOldestIfNeeded() {
		//remove oldest if needed
		if (keyNodeMap.size() > capacity) {
			keyNodeMap.remove(oldestNode.key);
			oldestNode = oldestNode.next;
			oldestNode.prev = null;
		}
	}

	private void setNewest(Node node) {
		if (node.prev != null && node.next != null) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		} else if (node == newestNode) { // check this first, in case we have capacity = 1 (don't want to node.next.prev on single node)
			return;
		} else if (node == oldestNode) {
			oldestNode = oldestNode.next; //missed this line, had to go through debugger to find this bug
			oldestNode.prev = null;
		}

		newestNode.next = node;
		node.prev = newestNode;
		node.next = null;
		newestNode = node;
	}
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class Node {
	public int key;
	public int val;
	public Node prev;
	public Node next;

	public Node(int key, int val) {
		this.key = key;
		this.val = val;
	}
}