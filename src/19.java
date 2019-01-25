import java.util.HashMap;
import java.util.Map;

/**
 * problem 19
 */
class RemoveNthnodeFromEndOfList {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head.next == null) {
			return null;
		}

		//hashmap of position and node
		Map<Integer, ListNode> map = new HashMap<>();
		ListNode curr = head;
		int pos = 0;
		while (curr != null) {
			map.put(pos, curr);
			curr = curr.next;
			pos++;
		}

		//O(n) memory, O(1) time to remove node

		//node to remove is head
		if (n == pos) {
			return head.next;
		} else {
			ListNode beforeToRemove = map.get(pos - n - 1);
			beforeToRemove.next = beforeToRemove.next.next;
			return head;
		}
	}
}
