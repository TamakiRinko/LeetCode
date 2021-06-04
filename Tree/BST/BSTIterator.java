package Tree.BST;

import java.util.Stack;

import DataStructure.TreeNode;


public class BSTIterator {
    /**
    * @param root: The root of binary tree.
    */
    TreeNode cur;
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        // do initialization if necessary
        cur = root;
    }

    /**
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return (!stack.empty() || cur != null);
    }

    /**
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        while(cur != null){
            stack.push(cur);
            cur = cur.left;
        }
        TreeNode temp = stack.pop();
        cur = temp.right;
        return temp;
    }
}
