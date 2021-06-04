package LinkedList;

import DataStructure.ListNode;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 */
public class DeleteDuplicates {
    /**
     * 直接找到下一个连接上即可
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)   return head;
        ListNode cur = head;
        ListNode next = head;

        while(next != null){
            while(next != null && cur.val == next.val){
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
        return head;
    }
}
