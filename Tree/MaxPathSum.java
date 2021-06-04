package Tree;

import DataStructure.TreeNode;

/**
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */

class ResultType{
    // singlePath：单线最长路径，可以不包含任何节点，maxPath：综合最大路径，至少包含一个节点
    int singlePath;
    int maxPath;
    ResultType(int sin, int max){
        singlePath = sin;
        maxPath = max;
    }
}
public class MaxPathSum {
    /**
     * 最大路径为：左右子树的最大路径和经过根的单线路径这三者的最大值
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        return helper(root).maxPath;
    }

    public ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }
        ResultType leftType = helper(root.left);
        ResultType rightType = helper(root.right);

        // 返回给上层的singlePath，若经过根的singlePath小于0，则返回0，返回给上层的singlePath不包含任何节点
        int singlePath = Math.max(leftType.singlePath, rightType.singlePath) + root.val;
        singlePath = Math.max(0, singlePath);
        // 返回给上层的maxPath，至少包含一个节点，
        // 可以使用；leftType.singlePath + rightType.singlePath + root.val，因为下层返回的singlePath >= 0
        int maxPath = Math.max(leftType.maxPath, rightType.maxPath);
        maxPath = Math.max(maxPath, leftType.singlePath + rightType.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }
}
