package LinkedList;

import DataStructure.ListNode;

/**
 * 判断给定的链表中是否有环。如果有环则返回true，否则返回false。
 * 你能给出空间复杂度O(1)的解法么？
 */
public class HasCycle {
    /**
     * 快慢指针
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast == null)    return false;
            fast = fast.next;
            slow = slow.next;
            if(fast == slow){
                return true;
            }
        }
        return false;
    }
}
