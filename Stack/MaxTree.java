package Stack;

import java.util.Stack;

import DataStructure.TreeNode;

public class MaxTree {
    public TreeNode maxTree(int[] A){
        //[2, 5, 6, 0, 3, 1]

        if(A == null || A.length == 0){
            return null;
        }

        TreeNode head = null;

        // 思路：利用单调栈找到左右边第一个比自己大的中的较小的一个，为自己的父亲！
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0; i <= A.length; ++i){
            // int curValue = (i == A.length) ? Integer.MAX_VALUE : A[i];
            TreeNode curNode = (i == A.length) ? new TreeNode(Integer.MAX_VALUE) : new TreeNode(A[i]);
            while(!stack.isEmpty() && curNode.val > stack.peek().val){
                // 头部，最后Integer.MAX_VALUE进栈前得到实际头
                if(stack.size() == 1){
                    head = stack.peek();
                }
                TreeNode node = stack.pop();
                if(!stack.isEmpty()){
                    if(stack.peek().val < curNode.val){
                        stack.peek().right = node;
                        // head = stack.peek();
                    }else{
                        curNode.left = node;
                        // head = curNode;
                    }
                }else{
                    curNode.left = node;
                    // head = curNode;
                }
                
            }
            stack.push(curNode);
        }
        return head;
    }
}
