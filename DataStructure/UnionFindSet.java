package DataStructure;

public class UnionFindSet {
    private int[] father = null;
    // 深度，代表以该节点为根的（子）树深度
    private int[] rank = null;
    // 并查集的集合数目，初始为所有节点数
    private int count;
    public UnionFindSet(int n){
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

    /**
     * 不按秩合并
     * @param a
     * @param b
     */
    public void unionWithoutRank(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            father[rootA] = rootB;
            count--;
        }
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
