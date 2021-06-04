package LinkedList;

import DataStructure.ListNode;

// class ListNode {
//     int val;
//     ListNode next;
//     ListNode(int x) { val = x; }
// }

/**
 * Delete_Last_N_Node
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 */
public class Delete_Last_N_Node {
    /**
     * 快慢指针
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for(int i = 0; i < n; ++i){
            fast = fast.next;
        }
        ListNode slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        if(fast == null){
            head = head.next;
        }else{
            slow.next = slow.next.next;
        }
        return head;
    }
    
}