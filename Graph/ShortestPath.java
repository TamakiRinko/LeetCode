package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import DataStructure.UndirectedGraphNode;

/**
 * 在图上求A和B之间的最短路径长度
 */
public class ShortestPath {
    /**
     * BFS即可
     * @param graph
     * @param A
     * @param B
     * @return
     */
    public int shortestPath(List<UndirectedGraphNode> graph, UndirectedGraphNode A, UndirectedGraphNode B) {
        // Write your code here
        Set<UndirectedGraphNode> set = new HashSet<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        
        set.add(A);
        queue.offer(A);

        int len = 0;

        while(!queue.isEmpty()){
            len++;
            int size = queue.size();
            for(int i = 0; i < size; ++i){
                UndirectedGraphNode cur = queue.poll();
                for(UndirectedGraphNode neighbor: cur.neighbors){
                    if(neighbor.equals(B)){
                        return len;
                    }
                    if(!set.contains(neighbor)){
                        set.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return -1;
    }
}
