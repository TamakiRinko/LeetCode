package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import DataStructure.TreeNode;

/**
 * 给定一棵二叉树，判断琪是否是自身的镜像（即：是否对称）
 */
public class IsSymmetric {
    /**
     * 迭代法，按层查找
     * @param root
     * @return
     */
    public boolean isSymmetric (TreeNode root) {
        // write code here
        // write code here
        if(root == null){
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            for(int i = 0; i < size; ++i){
                TreeNode cur = queue.poll();
                list.add(cur);
            }
            int left = 0, right = size - 1;
            while(left <= right){
                if(list.get(left).left != null){
                    if(list.get(right).right == null || list.get(right).right.val != list.get(left).left.val){
                        return false;
                    }
                }else{
                    if(list.get(right).right != null){
                        return false;
                    }
                }
                if(list.get(left).right != null){
                    if(list.get(right).left == null || list.get(right).left.val != list.get(left).right.val){
                        return false;
                    }
                }else{
                    if(list.get(right).left != null){
                        return false;
                    }
                }
                left++;
                right--;
            }
            for(int i = 0; i < size; ++i){
                TreeNode cur = list.get(i);
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return true;
    }

    /**
     * 递归模式！两个参数！！这道题不是根和左右子树的关系，而是直接左右子树节点的关系！
     * @param a
     * @param b
     * @return
     */
    public boolean helper(TreeNode a, TreeNode b){
        if(a == null && b == null){
            return true;
        }
        if((a != null && b == null) || (a == null && b != null)){
            return false;
        }
        return a.val == b.val && helper(a.left, b.right) && helper(a.right, b.left);
    }
}
