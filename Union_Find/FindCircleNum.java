package Union_Find;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 */


class UF{
    private int[] father = null;
    // 深度，代表以该节点为根的（子）树深度
    private int[] rank = null;
    // 并查集的集合数目
    private int count;
    public UF(int n){
        father = new int[n];
        rank = new int[n];
        count = n;
        for(int i = 0; i < n; ++i){
            // 刚开始祖先均为自己
            father[i] = i;
            rank[i] = 1;
        }
    }
    public int find(int x){
        if(father[x] == x){
            return x;
        }else{
            // 路径压缩，更新father[x]的值
            return father[x] = find(father[x]);
        }
    }
    public void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB)  return;
        // 按秩合并，把秩较小的合并到秩较大的上，使得更少的节点深度增加
        if(rank[rootA] <= rank[rootB]){
            father[rootA] = rootB;
        }else{
            father[rootB] = rootA;
        }
        if(rank[rootA] == rank[rootB]){
            // a和b的深度相同，按上述可知是将a的祖先rootA合并到b的祖先rootB下，rootB作为新的祖先，深度+1
            // 只需要维护根的深度即可，所有的子节点最终会找到其父节点，才能使用rank变量比较！
            rank[rootB]++;
        }
        count--;
    }
    public boolean query(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        return rootA == rootB;
    }
    public int getCount(){
        return count;
    }
}

public class FindCircleNum {
    public int findCircleNum(int[][] isConnected) {
        int result = 0;
        if(isConnected == null || isConnected.length == 0)  return result;

        int n = isConnected.length;
        UF uf = new UF(n);
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                if(isConnected[i][j] == 1){
                    uf.union(i, j);
                }
            }
        }
        result = uf.getCount();
        return result;
    }
}
