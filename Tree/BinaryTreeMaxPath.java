package Tree;

import DataStructure.TreeNode;

public class BinaryTreeMaxPath {
    private class ResultType {
        // singlePath：从根到左右子树的最大路径，这条路径可以不包含任何点
        // maxPath：从树中任意点到任意点的最大路径，至少包含一个点
        int singlePath, maxPath;
        ResultType(int singlePath, int maxPath){
            this.singlePath = singlePath;
            this.maxPath = maxPath;
        }
    }

    private ResultType helper(TreeNode root){
        if(root == null){
            return new ResultType(0, Integer.MIN_VALUE);
        }

        // 递归分治，找到的是左子树和右子树的max（maxPath）和他们对应的经过根的max（singlePath）
        ResultType leftResult = helper(root.left);
        ResultType rightResult = helper(root.right);

        // 这里左右子树的singlePath就不用与0比较了，左右子树的singlePath计算时若小于0，则最后leftResult.singlePath就是0
        // singlePath为向上传递时的左/右子树的singlePath，必须为一条直线，只能选择一个方向
        int singlePath = Math.max(leftResult.singlePath, rightResult.singlePath) + root.val;
        // 如果当前算出来小于0，则向上传递时singlePath就不要了，与上一句相互呼应
        singlePath = Math.max(singlePath, 0);
        
        // 三者比较
        int maxPath = Math.max(leftResult.maxPath, rightResult.maxPath);
        // maxPath = Math.max(maxPath, singlePath);  不是这个！
        // 此时求三者最大，必须横跨左右子树和根！
        maxPath = Math.max(maxPath, leftResult.singlePath + rightResult.singlePath + root.val);

        return new ResultType(singlePath, maxPath);
    }
}
