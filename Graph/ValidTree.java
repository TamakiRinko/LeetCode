package Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import DataStructure.UndirectedGraphNode;

/**
 * 给出 n 个节点，标号分别从 0 到 n - 1 并且给出一个 无向 边的列表 (给出每条边的两个顶点), 写一个函数去判断这张｀无向｀图是否是一棵树
 * 你可以假设我们不会给出重复的边在边的列表当中. 无向边　[0, 1] 和 [1, 0]　是同一条边， 因此他们不会同时出现在我们给你的边的列表当中。
 */
public class ValidTree {
    /**
     * n-1条边的条件下，构造无向图节点，从一个节点出发BFS，走到所有节点即可
     * @param n
     * @param edges
     * @return
     */
    public boolean validTreeBFS(int n, int[][] edges) {
        // write your code here
        if(n <= 1)  return true;
        if(edges.length != n - 1) return false;
        UndirectedGraphNode[] nodes = new UndirectedGraphNode[n];
        int edgeNum = edges.length;
        for(int i = 0; i < n; ++i){
            nodes[i] = new UndirectedGraphNode(i);
        }
        for(int i = 0; i < edgeNum; ++i){
            nodes[edges[i][0]].neighbors.add(nodes[edges[i][1]]);
            nodes[edges[i][1]].neighbors.add(nodes[edges[i][0]]);
        }

        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(nodes[0]);
        set.add(nodes[0]);

        while(!queue.isEmpty()){
            UndirectedGraphNode cur = queue.poll();
            for(UndirectedGraphNode neighbor: cur.neighbors){
                if(!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        // 一定是n - 1条边，n个节点
        if(set.size() == n){
            return true;
        }
        return false;
    }

}
