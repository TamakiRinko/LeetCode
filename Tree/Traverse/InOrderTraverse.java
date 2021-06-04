package Tree.Traverse;

import java.util.ArrayList;
import java.util.Stack;

import DataStructure.TreeNode;

public class InOrderTraverse {
    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if(root == null){
            return result;
        }
        TreeNode cur = root;

        while(!stack.empty() || cur != null){
            // 先往左子树遍历
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.empty()){
                // 左子树遍历完毕，存入结果集，往右子树遍历
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }
        return result;
    }
}
