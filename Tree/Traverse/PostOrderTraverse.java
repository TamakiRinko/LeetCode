package Tree.Traverse;

import java.util.ArrayList;
import java.util.Stack;

import DataStructure.TreeNode;

public class PostOrderTraverse {
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        TreeNode prev = null;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            // 此时不要pop！
            cur = stack.peek();
            // 还在递归向下遍历的过程中
            if(prev == null || prev.left == cur || prev.right == cur){
                // 先考虑左子树
                if(cur.left != null){
                    stack.push(cur.left);
                }else if(cur.right != null){
                    stack.push(cur.right);
                }
            }
            // 左子树遍历完成，进入右子树
            else if(cur.left == prev){
                if(cur.right != null){
                    stack.push(cur.right);
                }
            }
            // 右子树遍历完成（或到达叶结点），加入结果集，弹栈
            else{
                result.add(cur.val);
                stack.pop();
            }
            // prev指向当前cur的位置
            prev = cur;
        }
        return result;
    }
}
