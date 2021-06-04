package LinkedList;

import DataStructure.ListNode;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 */
public class ReverseKGroup {
    /**
     * 非递归法
     * 核心问题：反转k个节点的链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k == 1) return head;

        ListNode newHead = new ListNode();
        ListNode fast = head;
        ListNode begin = head;
        ListNode prev = newHead;
        ListNode nextPrev = newHead;
        while(fast != null){
            begin = fast;
            prev = nextPrev;
            for(int i = 0; i < k; ++i){
                if(fast == null){
                    return newHead.next;
                }
                fast = fast.next;
            }
            // nextPrev为反转后的最后一个节点（也即当前的第一个节点），它是下一次反转的begin前的节点
            nextPrev = begin;

            // 以下反转从begin到fast前的k个节点
            ListNode cur = begin.next;
            begin.next = fast;
            while(cur != fast){
                ListNode next = cur.next;
                cur.next = begin;
                begin = cur;
                cur = next;
            }
            prev.next = begin;
            
        }

        return newHead.next;
    }

    /**
     * 递归法！
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroupRecursive(ListNode head, int k) {
        if(head==null){return null;}
        ListNode a = head;
        ListNode b = head;
        for(int i = 0;i < k; i++){
            if(b == null){return head;}
            b = b.next;
        }
        // 反转a和b间的节点（不包括b），返回b的前一个结点，这是反转后的第一个节点
        ListNode start = reverseAB(a, b);
        // a此时为反转后的最后一个节点，继续递归
        a.next = reverseKGroup(b, k);
        return start;
    }
    //翻转[a，b)之间的结点，区间左闭右开，不包括b，返回原顺序中b节点的前一个节点
    public ListNode reverseAB(ListNode a,ListNode b){
        ListNode current = a;
        ListNode pre = null;
        ListNode next = null;
        while(current!=b){
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }
}
