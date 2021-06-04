package Hash;

import java.util.HashMap;

import DataStructure.DoubleLinkListNode;

public class LRUCache {
    // 使用双向链表+Hash表实现
    // 链表头为最老的数据

    private DoubleLinkListNode head;
    // private DoubleLinkListNode tail;
    private HashMap<Integer, DoubleLinkListNode> hashMap;
    private int capacity;
    private int count;              // 当前数量

    /*
     * @param capacity: An integer
     */public LRUCache(int capacity) {
        // do initialization if necessary
        this.capacity = capacity;
        count = 0;
        head = new DoubleLinkListNode();
        head.prev = head.next = head;
        hashMap = new HashMap<>();
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        DoubleLinkListNode node;
        if((node = hashMap.get(key)) != null){
            deleteNode(node);
            addNode(head.prev, node);
            return node.val;
        }else{
            return -1;
        }
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        DoubleLinkListNode node;
        if((node = hashMap.get(key)) != null){
            // 已经有了该key，更新，放到末尾
            deleteNode(node);
            node.val = value;
            addNode(head.prev, node);
        }else{
            // 没有该key，添加到末尾
            node = new DoubleLinkListNode(value);
            hashMap.put(key, node);
            if(count < capacity){
                addNode(head.prev, node);
            }else{
                // hash中删除
                hashMap.values().removeIf(val->val.equals(head.next));
                deleteNode(head.next);
                addNode(head.prev, node);
            }
        }

    }

    // 在cur后添加node
    private void addNode(DoubleLinkListNode cur, DoubleLinkListNode node){
        DoubleLinkListNode next = cur.next;
        node.next = next;
        node.prev = cur;
        next.prev = node;
        cur.next = node;
        count++;
    }
    // 删除node
    private void deleteNode(DoubleLinkListNode node){
        DoubleLinkListNode next = node.next;
        DoubleLinkListNode prev = node.prev;
        node.next = null;
        node.prev = null;
        next.prev = prev;
        prev.next = next;
        count--;
    }

    public static void main(String[] args) {
       LRUCache cache = new LRUCache(2);
       cache.set(2, 1);
       cache.set(1, 1);
       System.out.println(cache.get(2));
       cache.set(4, 1);
       System.out.println(cache.get(1));
       System.out.println(cache.get(2));
        // LRUCache cache = new LRUCache(1);
        // cache.set(2, 1);
        // System.out.println(cache.get(2));
        // cache.set(3, 2);
        // System.out.println(cache.get(2));
        // System.out.println(cache.get(3));
    }
}