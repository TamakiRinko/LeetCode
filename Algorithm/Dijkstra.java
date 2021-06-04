package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

// 与index的距离为dis
class Node{
    int index;
    int dis;
    public Node(int index, int dis){
        this.index = index;
        this.dis = dis;
    }
}


public class Dijkstra {
    static int[] dx = new int[]{1, 0, 0, -1};
    static int[] dy = new int[]{0, 1, -1, 0};
    /**
     * 给出n*n的带权值的矩阵，一步只能走四相邻的格子，花费为abs(两格权值差)，问从(1,1)走到(n,n)的最小代价。
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] weight = new int[n][n];
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j)
                weight[i][j] = in.nextInt();
        }
        int N = n * n;
        // 到各个位置的距离
        int[] dis = new int[N + 1];
        // 该位置是否被访问过
        int[] visited = new int[N + 1];
        // mp.get(i): i到四个方向的开销
        List<List<Node>> mp = new ArrayList<>();
        // N + 1个，下标使用1~N
        for(int i = 0; i <= N; ++i){
            mp.add(new ArrayList<>());
        }
        for(int i = 1; i <= n; ++i){
            for(int j = 1; j <= n; ++j){
                for(int k = 0; k < 4; ++k){
                    int di = i + dx[k];
                    int dj = j + dy[k];
                    if(di < 1 || di > n || dj < 1 || dj > n)  continue;
                    int distance = Math.abs(weight[i][j] - weight[di][dj]);
                    mp.get(index(i, j, n)).add(new Node(index(di, dj, n), distance));
                    mp.get(index(di, dj, n)).add(new Node(index(i, j, n), distance));
                }
            }
        }

        for(int i = 1; i <= N; ++i){
            dis[i] = Integer.MAX_VALUE;
        }
        // 1到1距离为0
        dis[1] = 0;

        
        // Queue<Node> queue = new LinkedList<>();
        // // 初始化：1到1的距离为0
        // queue.offer(new Node(1, 0));
        // while(!queue.isEmpty()){
        //     Node cur = queue.poll();
        //     int curIndex = cur.index;
        //     if(visited[curIndex] == 1){
        //         continue;
        //     }
        //     visited[curIndex] = 1;
        //     // 通过cur节点，更新从1到cur.neighbor的最短路径
        //     for(Node neighbor: mp.get(curIndex)){
        //         if(visited[neighbor.index] != 1){
        //             if(dis[neighbor.index] > dis[curIndex] + neighbor.dis){
        //                 // 产生了新的更短的路径
        //                 dis[neighbor.index] = dis[curIndex] + neighbor.dis;
        //                 // 放入队列中
        //                 queue.offer(new Node(neighbor.index, dis[neighbor.index]));
        //             }
        //         }
        //     }
        // }

        // 循环N次，每次取出当前没访问过的minIndex，用它来更新其他dis
        for(int i = 0; i < N; ++i){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for(int j = 1; j <= N; ++j){
                if(visited[j] == 0 && dis[j] < min){
                    min = dis[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = 1;
            for(Node neighbor: mp.get(minIndex)){
                if(visited[neighbor.index] != 1){
                    if(dis[neighbor.index] > dis[minIndex] + neighbor.dis){
                        dis[neighbor.index] = dis[minIndex] + neighbor.dis;
                    }
                }
            }
        }
        System.out.println(index(n, n, n));
    }
    // 二维转一维，下标从1开始
    public static int index(int i, int j, int n){
        return n * (i - 1) + j;
    }

    /**
     * 
     * @param weight  weight[i][j]：从i到j的距离
     * @param start   start：开始点
     */
    public static void dijkstra(int[][] weight, int start){
        int n = weight.length;
        int[] dis = new int[n];
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; ++i){
            dis[i] = weight[start][i];
        }
        visited[start] = true;

        for(int i = 0; i < n - 1; ++i){
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            // 找到当前未选中的最小的那个，加入以访问节点列表
            for(int j = 0; j < n; ++j){
                if(!visited[j] && dis[j] < min){
                    min = dis[j];
                    minIndex = j;
                }
            }
            // 无法到达任何节点了
            if(min == Integer.MAX_VALUE)    break;
            visited[minIndex] = true;
            // 使用minIndex来更新其他的dis
            for(int j = 0; j < n; ++j){
                // 从minIndex无法到达j
                if(weight[minIndex][j] == Integer.MAX_VALUE)   continue;
                if(dis[minIndex] + weight[minIndex][j] < dis[j]){
                    dis[j] = dis[minIndex] + weight[minIndex][j];
                }
            }
        }
    }
}
