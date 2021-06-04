package LinkedList;

import DataStructure.ListNode;

public class SortList {
    // 找到链表中点mid并返回
    public ListNode findListMid(ListNode head){

        if(head == null || head.next == null){
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        // n - 1 / 2位置
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 合并两个有序链表
    public ListNode mergeTwoList(ListNode leftHead, ListNode rightHead){
        // 头节点
        ListNode head = new ListNode();
        ListNode tail = head;
        ListNode leftCur = leftHead;
        ListNode rightCur = rightHead;
        while(leftCur != null && rightCur != null){
            if(leftCur.val <= rightCur.val){
                tail.next = leftCur;
                leftCur = leftCur.next;
            }else{
                tail.next = rightCur;
                rightCur = rightCur.next;
            }
            tail = tail.next;
        }
        // 不用循环了！直接连上末尾的即可！
        // while(leftCur != null){
        //     tail.next = leftCur;
        //     tail = tail.next;
        //     leftCur = leftCur.next;
        // }
        // while(rightCur != null){
        //     tail.next = rightCur;
        //     tail = tail.next;
        //     rightCur = rightCur.next;
        // }
        if(leftCur != null){
            tail.next = leftCur;
        }
        if(rightCur != null){
            tail.next = rightCur;
        }
        return head.next;
    }


    // 排好以head为头节点的链表的顺序，并返回头节点
    public ListNode sortList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode mid = findListMid(head);
        ListNode rightListHead = mid.next;
        mid.next = null;

        ListNode left = sortList(head);
        ListNode right = sortList(rightListHead);

        return mergeTwoList(left, right);

    }
}
