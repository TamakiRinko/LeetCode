package BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import DataStructure.UndirectedGraphNode;

public class LevelOrderUndirectedGraph {
    public static void levelOrder(UndirectedGraphNode node){
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        
        queue.offer(node);
        set.add(node);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                UndirectedGraphNode cur = queue.poll();
                for(UndirectedGraphNode neighbor: cur.neighbors){
                    if(!set.contains(neighbor)){
                        set.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            
            // 不考虑level
            // UndirectedGraphNode cur = queue.poll();
            // for(UndirectedGraphNode neighbor: cur.neighbors){
            //     if(!set.contains(neighbor)){
            //         set.add(neighbor);
            //         queue.offer(neighbor);
            //     }
            // }
        }
    }
}
