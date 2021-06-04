package Graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断图是否为二分图
 */
public class IsBipartite {
    /**
     * BFS，染色法，每个节点0或+-1三种颜色，若出现邻接节点相同，则不是二分图，否则对邻接节点反转颜色。
     * @param graph
     * @return
     */
    public boolean isBipartiteBFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; ++i){
            if(color[i] == 0){
                queue.offer(i);
                color[i] = 1;
                while(!queue.isEmpty()){
                    int x = queue.poll();
                    for(int l: graph[x]){
                        if(color[l] == color[x]){
                            return false;
                        }else if(color[l] == 0){
                            color[l] = -color[x];
                            queue.offer(l);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * DFS，染色法，每个节点0或+-1三种颜色，若出现邻接节点相同，则不是二分图，否则对邻接节点反转颜色。
     * @param graph
     * @return
     */
    public boolean isBipartiteDFS(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for(int i = 0; i < n; ++i){
            // 如果没有颜色，则赋为1
            if(color[i] == 0 && !dfs(graph, color, i, 1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 给v赋为c，从v开始进行dfs，给v的邻接节点赋为-c
     * 若出现矛盾，则不是二分图
     * @param graph
     * @param color
     * @param v
     * @param c
     * @return
     */
    public boolean dfs(int[][] graph, int[] color, int v, int c){
        if(color[v] != 0){
            return color[v] == c;
        }
        color[v] = c;
        for(int l: graph[v]){
            if(!dfs(graph, color, l, -c)){
                return false;
            }
        }
        return true;
    }
}
