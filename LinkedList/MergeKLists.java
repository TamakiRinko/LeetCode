package LinkedList;

import java.util.ArrayList;
import java.util.PriorityQueue;

import DataStructure.ListNode;

// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

// 没有使用PriorityQueue，实现了类似的功能
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0){
            return null;
        }else if(lists.length == 1){
            return lists[0];
        }
        ListNode head = null;
        ListNode cur = null;
        ListNode tail = head;
        int n = lists.length;
        ListNode[] index = new ListNode[n];
        for(int i = 0; i < n; ++i){
            index[i] = lists[i];
        }
        int first;
        while((first = getFirstNotEndList(index, n)) != -1){
            int finalIndex = first;
            cur = index[first];
            // 找到头部最小的那个
            for(int i = first + 1; i < n; ++i){
                if(index[i] != null && cur.val > index[i].val){
                    cur = index[i];
                    finalIndex = i;
                }
            }
            index[finalIndex] = index[finalIndex].next;
            if(tail == null){
                head = cur;
                tail = head;
            }else{
                tail.next = cur;
                tail = tail.next;
            }
        }
        return head;
    }

    public boolean endsAll(ListNode[] index, int n){
        for(int i = 0; i < n; ++i){
            if(index[i] != null){
                return false;
            }
        }
        return true;
    }

    public int getFirstNotEndList(ListNode[] index, int n){
        for(int i = 0; i < n; ++i){
            if(index[i] != null){
                return i;
            }
        }
        return -1;
    }


    class MyNode implements Comparable<MyNode>{
        int val;
        ListNode node;
        public MyNode(ListNode node){
            this.val = node.val;
            this.node = node;
        }
        public int compareTo(MyNode n1){
            return this.val - n1.val;
        }
    }

    public ListNode mergeKLists2(ListNode[] lists){
        PriorityQueue<MyNode> queue = new PriorityQueue<>();
        // PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (n1, n2)->{return n1.val - n2.val;});
        for(ListNode node: lists){
            if(node != null){
                queue.offer(new MyNode(node));
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!queue.isEmpty()){
            MyNode min = queue.poll();
            tail.next = min.node;
            tail = tail.next;
            if(min.node.next != null){
                queue.offer(new MyNode(min.node.next));
            }
        }
        return head.next;
    }

    /**
     * 使用比较器
     * @param lists
     * @return
     */
    public ListNode mergeKLists2_2(ListNode[] lists){
        if(lists == null || lists.length == 0)  return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b)->{return a.val - b.val;});
        for(int i = 0; i < lists.length; ++i){
            if(lists[i] != null){
                queue.offer(lists[i]);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while(!queue.isEmpty()){
            ListNode min = queue.poll();
            tail.next = min;
            tail = tail.next;
            if(min.next != null){
                queue.offer(min.next);
            }
        }
        return head.next;
    }

    public ListNode mergeKLists3(ListNode[] lists){
        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2)->{
            return n1.val - n2.val;
        });
        for(ListNode node: lists){
            if(node != null){
                queue.offer(node);
            }
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;
        while(!queue.isEmpty()){
            ListNode min = queue.poll();
            tail.next = min;
            tail = tail.next;
            if(min.next != null){
                queue.offer(min.next);
            }
        }
        return head.next;
    }


    /**
     * 分治法
     */
    // merge lists所有的链表，返回merge后的头节点
    public ListNode mergeKLists4(ArrayList<ListNode> lists){
        if(lists == null || lists.size() == 0){
            return null;
        }else if(lists.size() == 1){
            return lists.get(0);
        }
        return mergeDivideLists(lists, 0, lists.size() - 1);
    }

    // merge lists从begin到end的所有链表，返回merge后的头节点
    public ListNode mergeDivideLists(ArrayList<ListNode> lists, int begin, int end){
        if(begin == end){
            return lists.get(begin);
        }
        int mid = begin + (end - begin) / 2;
        ListNode left = mergeDivideLists(lists, begin, mid);
        ListNode right = mergeDivideLists(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    public ListNode mergeTwoLists(ListNode leftHead, ListNode rightHead){
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
         if(leftCur != null){
             tail.next = leftCur;
         }
         if(rightCur != null){
             tail.next = rightCur;
         }
         return head.next;
    }

}
