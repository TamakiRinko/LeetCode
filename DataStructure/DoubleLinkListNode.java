package DataStructure;

public class DoubleLinkListNode {
    public int val;
    public DoubleLinkListNode prev;
    public DoubleLinkListNode next;
    public DoubleLinkListNode() {}
    public DoubleLinkListNode(int val) { this.val = val; this.prev = this.next = null; }
    public DoubleLinkListNode(int val, DoubleLinkListNode prev, DoubleLinkListNode next) { 
        this.val = val; this.prev = prev; this.next = next;
    }
}
