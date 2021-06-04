package Tree;

import java.util.Stack;

import DataStructure.TreeNode;

public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        int val = 0;
        if (root == null)
            return 0;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        boolean flag = false;
        while (!stack.empty() || cur != null) {
            if (cur != null && cur.left != null) {
                flag = true;
            }
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.empty()) {
                cur = stack.pop();
                if (cur.right == null && flag == true) {
                    val += cur.val;
                    // 左叶子节点，继续弹出
                    cur = null;
                } else {
                    cur = cur.right;
                }
                flag = false;
            }
        }
        return val;
    }

    public int sumOfLeftLeaves2(TreeNode root) {
        return f(root);
    }
    public int f(TreeNode root){
        if(root == null)    return 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            return root.left.val + f(root.right);
        }else{
            return f(root.left) + f(root.right);
        }
    }
}
