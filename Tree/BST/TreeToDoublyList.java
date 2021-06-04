package Tree.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import DataStructure.Node;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 */
public class TreeToDoublyList {
    /**
     * 保存下来每个中序遍历到的节点，最后修改指针
     * @param root
     * @return
     */
    public Node treeToDoublyList(Node root) {
        if(root == null)    return null;
        List<Node> container = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node cur = root;
        while(!stack.isEmpty() || cur != null){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
                cur = stack.pop();
                container.add(cur);
                cur = cur.right;
            }
        }
        int n = container.size();
        for(int i = 1; i < n; ++i){
            container.get(i).left = container.get(i - 1);
        }
        for(int i = 0; i < n - 1; ++i){
            container.get(i).right = container.get(i + 1);
        }
        container.get(0).left = container.get(n - 1);
        container.get(n - 1).right = container.get(0);
        return container.get(0);
    }

    Node pre, head;
    /**
     * 在遍历过程中修改指针，递归中序遍历
     * @param root
     * @return
     */
    public Node treeToDoublyListInTime(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(Node cur) {
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        // 修改pre为当前节点，进入右子树
        pre = cur;
        dfs(cur.right);
    }
}
