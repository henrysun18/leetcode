import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
		//divide and conquer to get runtime O(nlogk); merging n elements logk times
		//intuition is that if we merge 2 lists at a time, we halve the amount of lists we need to merge again
		//this halving keeps going each time we merge, and thus we only need to repeat this logk times
		Queue<ListNode> headsToDivideAndMerge = new LinkedList<>(Arrays.asList(lists));
		while (headsToDivideAndMerge.size() > 1) {
			int numLists = headsToDivideAndMerge.size();
			for (int pair = 0; pair < numLists/2; pair++) {
				ListNode list1 = headsToDivideAndMerge.poll();
				ListNode list2 = headsToDivideAndMerge.poll();

				ListNode sortedSublist = mergeSortedLists(list1, list2);
				if (sortedSublist != null) {
					headsToDivideAndMerge.add(sortedSublist);
				}
			}
			//if odd, assume remaining list is already merged and continue
			//that is, don't poll it from the queue
		}

		return headsToDivideAndMerge.poll();
	}

	private ListNode mergeSortedLists(ListNode list1, ListNode list2) {
		if (list1 == null) return list2;
		if (list2 == null) return list1;

		ListNode head = new ListNode(0); //temporary
		ListNode curr = head;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				curr.next = list1;
				list1 = list1.next;
				curr = curr.next;
			} else {
				curr.next = list2;
				list2 = list2.next;
				curr = curr.next;
			}
		}

		if (list1 != null) {
			curr.next = list1;
		} else if (list2 != null) {
			curr.next = list2;
		}

		return head.next;
	}
}
