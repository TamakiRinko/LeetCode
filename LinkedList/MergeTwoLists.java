package LinkedList;

import java.util.List;

import DataStructure.ListNode;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)  return l2;
        if(l2 == null)  return l1;
        ListNode head = new ListNode();
        ListNode cur = head;
        ListNode curl1 = l1;
        ListNode curl2 = l2;
        while(curl1 != null && curl2 != null){
            if(curl1.val > curl2.val){
                cur.next = curl2;
                curl2 = curl2.next;
            }else{
                cur.next = curl1;
                curl1 = curl1.next;
            }
            cur = cur.next;
        }
        if(curl1 != null){
            cur.next = curl1;
        }else{
            cur.next = curl2;
        }

        return head.next;
    }
}
