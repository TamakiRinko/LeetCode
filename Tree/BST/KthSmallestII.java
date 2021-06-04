package Tree.BST;

import DataStructure.TreeNode;

/**
 * 给定一个二叉搜索树，我们希望找到其中第 k 小的元素。 BST中可以存在相同的元素
 * 在这道题目中，除了原始的二叉搜索树 root 以外，你还会得到一个和其结构完全一致的二叉树nodenum_root，树上节点的值代表以该节点为根的子树的节点数量。
 */
public class KthSmallestII {
    int result = 0;
    public int kthSmallestII(TreeNode root, TreeNode nodenum_root, int k) {
        helper(root, nodenum_root, k);
        return result;
    }
    /**
     * 递归搜索，nodenum_root的作用是看左孩子是多少，即有多少肯定比自己小的！
     * 在root为根的子树中找第k大
     * @param root
     * @param nodenum_root
     * @param k
     */
    public void helper(TreeNode root, TreeNode nodenum_root, int k){
        if(root == null)    return;
        if(root.left != null){
            if(nodenum_root.left.val >= k){
                // 在左子树中找第k大
                helper(root.left, nodenum_root.left, k);
            }else if(nodenum_root.left.val == k - 1){
                // 就是自己
                result = root.val;
                return;
            }else{
                // 在有子树中找第k - 左.val - 1大
                helper(root.right, nodenum_root.right, k - nodenum_root.left.val - 1);
            }
        }else if(root.right != null){
            if(k == 1){
                result = root.val;
                return;
            }else{
                helper(root.right, nodenum_root.right, k - 1);
            }
        }else{
            if(k == 1){
                result = root.val;
                return;
            }
        }
    }
}
