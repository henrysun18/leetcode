import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class MergeKSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {
		//go through all lists, find the lowest value head
		//need to go through k lists, n times where n is the total amount of elements
		// time complexity O(n*k), space complexity O(n) since we have to recurse n times
		int indexOfListWithSmallestHead = 0;
		ListNode smallestHeadNode = null;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null) {
				if (smallestHeadNode == null) {
					smallestHeadNode = lists[i];
					indexOfListWithSmallestHead = i;
				}
				if (lists[i].val < lists[indexOfListWithSmallestHead].val) {
					smallestHeadNode = lists[i];
					indexOfListWithSmallestHead = i;
				}
			}
		}

		if (smallestHeadNode == null) {
			//all lists are null, base case where we return null
			return null;
		}
		lists[indexOfListWithSmallestHead] = lists[indexOfListWithSmallestHead].next;
		smallestHeadNode.next = mergeKLists(lists);
		return smallestHeadNode;
	}

	public ListNode mergeKListsEfficient(ListNode[] lists) {
		//O(n) time since we make two passes, one to populate map and one to merge
		//O(n) space since we're storing references to all n nodes in the treemap
		TreeMap<Integer, List<ListNode>> map = new TreeMap<>();
		//for each element in lists,
		//store it in a treemap, hash by value and store append to list
		for (ListNode listNode : lists) {
			while (listNode != null) {
				if (!map.containsKey(listNode.val)) {
					map.put(listNode.val, new LinkedList<>());
				}
				map.get(listNode.val).add(listNode);
				listNode = listNode.next;
			}
		}
		//go through treemap in order and merge
		ListNode head = null;
		ListNode curr = null;
		for (Map.Entry<Integer, List<ListNode>> treeEntry : map.entrySet()) {
			List<ListNode> listOfNodes = treeEntry.getValue();
			for (ListNode node : listOfNodes) {
				if (head == null) {
					head = node;
					curr = head;
				} else {
					curr.next = node;
					curr = curr.next;
				}
			}
		}
		return head;
	}
}
