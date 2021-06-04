package Tree;

import java.util.LinkedList;
import java.util.Stack;

import DataStructure.TreeNode;
/**
 * 返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
 */
public class BstFromPreorder {
    /**
     * 使用递归
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorderRecursive(int[] preorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }

        int n = preorder.length;
        // 每个节点往右看第一个比它大的下标
        int[] large = new int[n];
        getFirstRightLarger(preorder, large);

        return build(preorder, large, 0, n - 1);
    }

    /**
     * 单调栈获得每个节点往右看第一个比它大的下标
     * @param preorder
     * @param large
     */
    public void getFirstRightLarger(int[] preorder, int[] large){
        Stack<Integer> queue = new Stack<>();
        int n = preorder.length;
        queue.push(0);
        for(int i = 1; i <= n; ++i){
            int cur = (i == n) ? Integer.MAX_VALUE : preorder[i];
            while(!queue.isEmpty() && preorder[queue.peek()] < cur){
                int index = queue.pop();
                large[index] = i;
            }
            queue.add(i);
        }
    }

    /**
     * 从begin到end构建树，并返回根节点
     * @param preorder
     * @param large
     * @param begin
     * @param end
     * @return
     */
    public TreeNode build(int[] preorder, int[] large, int begin, int end){
        if(begin > end) return null;
        // 叶节点
        if(begin == end){
            TreeNode leaf = new TreeNode(preorder[begin]);
            return leaf;
        }
        TreeNode root = new TreeNode(preorder[begin]);
        root.left = build(preorder, large, begin + 1, large[begin] - 1);
        root.right = build(preorder, large, large[begin], end);
        return root;
    }

    /**
     * 使用栈，单调栈和构造树同时进行！
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorderStack(int[] preorder) {
        if(preorder == null || preorder.length == 0){
            return null;
        }
        int n = preorder.length;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        for(int i = 1; i < n; i++){
            TreeNode child = new TreeNode(preorder[i]);
            //栈顶元素为父节点，当前先序遍历中的元素作为子节点
            //如果栈顶的元素值小于子节点的元素值，则将栈顶的元素弹出并作为新的父节点
            TreeNode parent = stack.peek();
            while(!stack.isEmpty() && stack.peek().val<child.val){
                parent = stack.pop();
            }
            //如果父节点的元素值小于子节点的元素值，则子节点为右孩子，否则为左孩子
            if(child.val < parent.val){
                parent.left = child;
            } else {
                parent.right = child;
            }
            stack.push(child);
        }
        return root;
    }
}
