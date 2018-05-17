package AddTwoNumbers;

/**
 * Created by Henry on 5/16/2018.
 * Problem 2
 */
/**
 * Definition for singly-linked list.
 */
class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}

class Solution {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int currDigit = l1.val + l2.val;
		int carry = currDigit / 10;
		ListNode head = new ListNode(currDigit % 10);
		ListNode curr = head;
		l1 = l1.next;
		l2 = l2.next;
		while (l1 != null || l2 != null || carry != 0) {
			currDigit = 0;
			if (l1 != null) {
				currDigit += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				currDigit += l2.val;
				l2 = l2.next;
			}
			curr.next = new ListNode((currDigit + carry) % 10);

			curr = curr.next;
			carry = (currDigit + carry) / 10;
		}
		return head;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);

		addTwoNumbers(l1, l2);
	}
}
