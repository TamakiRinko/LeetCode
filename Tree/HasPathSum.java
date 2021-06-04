package Tree;

import DataStructure.TreeNode;

/**
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * 叶子节点 是指没有子节点的节点。
 */
public class HasPathSum {
    /**
     * 看左右是否有target - root.val
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null){
            return false;
        }
        return helper(root, targetSum);
    }

    /**
     * [1, 2] targetSum = 1时，返回false
     * @param root
     * @param target
     * @return
     */
    public boolean helper(TreeNode root, int target){
        if(root.left == null && root.right == null){
            return target == root.val;
        }
        boolean left = (root.left == null) ? false : helper(root.left, target - root.val);
        boolean right = (root.right == null) ? false : helper(root.right, target - root.val);
        return left || right;
    }

    /**
     * [1, 2] targetSum = 1时，也返回true
     * @param root
     * @param target
     * @return
     */
    public boolean helper2(TreeNode root, int target){
        if(root == null){
            return target == 0;
        }
        return helper(root.left, target - root.val) || helper(root.right, target - root.val);
    }
}
