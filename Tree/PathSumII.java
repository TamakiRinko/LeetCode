package Tree;

import java.util.ArrayList;
import java.util.List;

import DataStructure.TreeNode;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)    return result;
        helper(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    public void helper(TreeNode root, int target, List<Integer> cur, List<List<Integer>> result){
        if(root.left == null && root.right == null){
            if(target == root.val){
                cur.add(root.val);
                result.add(new ArrayList<>(cur));
                // remove不能丢！
                cur.remove(cur.size() - 1);
                return;
            }
        }
        cur.add(root.val);
        if(root.left != null){
            helper(root.left, target - root.val, cur, result);
        }
        if(root.right != null){
            helper(root.right, target - root.val, cur, result);
        }
        // remove不能丢！
        cur.remove(cur.size() - 1);
    }
}
