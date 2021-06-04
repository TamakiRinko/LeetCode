package Tree.Traverse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import DataStructure.TreeNode;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class ZigzagLevelOrder {
    /**
     * 和一般层次序遍历一样，特定时候逆序即可
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 0;
        while(!queue.isEmpty()){
            List<Integer> cur = new ArrayList<>();
            List<TreeNode> temp = new ArrayList<>();
            // 强调同时拿到一层的数据
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.poll();
                temp.add(node);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            // index % 2 == 1时逆序输出
            if(index % 2 == 1){
                Collections.reverse(temp);
            }
            // 耗时操作！
            cur.addAll(temp.stream().map(x->x.val).collect(Collectors.toList()));
            // for(int i = 0; i < size; ++i){
                // TreeNode x = temp.get(i);
                // cur.add(x.val);
            // }
            results.add(cur);
            index++;
        }
        return results;
    }
}
