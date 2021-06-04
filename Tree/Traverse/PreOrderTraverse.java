package Tree.Traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import DataStructure.TreeNode;

public class PreOrderTraverse {
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root == null){
            return result;
        }

        TreeNode cur = null;
        stack.push(root);

        while(!stack.empty()){
            cur = stack.pop();
            result.add(cur.val);
            // 先右再左
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }

        return result;
    }

    public List<Integer> preorderTraversal_2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        TreeNode cur = root;

        while(!stack.empty() || cur != null){
            // 递归向左子树遍历
            while(cur != null){
                result.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            }
            // 左子树到底了，进入右子树
            if(!stack.empty()){
                cur = stack.pop();
                cur = cur.right;
            }
        }

        return result;
    }
}
