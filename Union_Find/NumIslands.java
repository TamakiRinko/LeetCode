package Union_Find;

import DataStructure.UnionFindSet;

/**
 *
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumIslands {
    /**
     * 并查集，所有的水都合并到一个虚拟节点，陆地也相互合并
     */
    int[][] direction = new int[][]{{0, 1}, {1, 0}};
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        // size为虚拟水节点
        int size = row * col;
        UnionFindSet unionFindSet = new UnionFindSet(size + 1);
        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                if(grid[i][j] == '1'){
                    for(int[] dir: direction){
                        int newX = i + dir[0];
                        int newY = j + dir[1];
                        if(isValid(newX, newY, row, col) && grid[newX][newY] == '1'){
                            unionFindSet.union(getIndex(i, j, col), getIndex(newX, newY, col));
                        }
                    }
                }else{
                    // 所有水都合并到一起
                    unionFindSet.union(getIndex(i, j, col), size);
                }
            }
        }
        return unionFindSet.getCount() - 1;
    }

    public boolean isValid(int x, int y, int row, int col){
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public int getIndex(int i, int j, int col){
        return i * col + j;
    }
}
