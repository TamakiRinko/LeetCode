package Tree.BST;

import DataStructure.TreeNode;

public class InOrderSuccessor {
    public TreeNode inOrderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        return helper(root, p, root);
    }

    public TreeNode helper(TreeNode root, TreeNode p, TreeNode prev){
        if(root == null){
            return null;
        }
        if(root.val == p.val){
            if(root.right != null){
                TreeNode cur = root.right;
                while(cur.left != null){
                    cur = cur.left;
                }
                return cur;
            }else{
                return prev;
            }
        }

        if(root.val < p.val){
            return helper(root.right, p, prev);
        }else{
            return helper(root.left, p, root);
        }
    }


    public TreeNode inOrderSuccessor2(TreeNode root, TreeNode p) {
        // 维护successor，表示最近的祖先
        TreeNode successor = null;
        while (root != null && root != p) {
            if (root.val > p.val) {
                // 向左走则更新祖先
                successor = root;
                root = root.left;
            } else {
                // 向右走不用更新！
                root = root.right;
            }
        }
        
        if (root == null) {
            return null;
        }
        // 没有右子树，返回祖先
        if (root.right == null) {
            return successor;
        }
        // 有右子树，返回右子树的最左孩子
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public TreeNode inOrderSuccessor3(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }

        if (root.val <= p.val) {
            // 相等也会继续往右走！最后一定会返回null
            return inOrderSuccessor3(root.right, p);
        } else {
            // left返回null，说明就是在该root的左子树中，root为其最近祖先，返回root
            // left返回非null，说明之前已经有其最近祖先了，直接返回
            TreeNode left = inOrderSuccessor3(root.left, p);
            return (left != null) ? left : root;
        }
    }
}
