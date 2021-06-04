package Tree.Traverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import DataStructure.TreeNode;

public class LevelOrder {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(-2);
        TreeNode node5 = new TreeNode(6);
        node1.left = node3;
        node1.right = node2;
        node2.right = node4;
        node3.left = node5;
        List<List<Integer>> results = levelOrder(node1);
        results.forEach(list->{
           list.forEach(System.out::println); 
        });
    }

    public static List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> results = new ArrayList<>();
        if(root == null){
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> cur = new ArrayList<>();
            // 强调同时拿到一层的数据
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                TreeNode node = queue.poll();
                cur.add(node.val);
                // if(node.left != null){
                //     queue.offer(node.left);
                // }
                // if(node.right != null){
                //     queue.offer(node.right);
                // }
                Optional.ofNullable(node.left).ifPresent(queue::offer);
                Optional.ofNullable(node.right).ifPresent(queue::offer);
            }
            // // 仅层次遍历，不强调同时拿到一层的数据
            // TreeNode node = queue.poll();
            // if(node.left != null){
            //     queue.offer(node.left);
            // }
            // if(node.right != null){
            //     queue.offer(node.right);
            // }
            results.add(cur);
        }
        return results;
    }
}
