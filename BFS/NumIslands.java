package BFS;

import java.util.LinkedList;
import java.util.Queue;

class Coordinate{
    int x;
    int y;
    public Coordinate(){}
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }
}

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class NumIslands {
    int[] directionX = new int[]{1, 0, 0, -1};
    int[] directionY = new int[]{0, 1, -1, 0};
    /**
     * 二维表上的BFS，发现某个点为1，则由他开始BFS，途中找到的新的点都变为0
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int result = 0;
        if(grid == null || grid.length == 0 || grid[0].length == 0){
            return result;
        }
        int row = grid.length;
        int col = grid[0].length;

        for(int i = 0; i < row; ++i){
            for(int j = 0; j < col; ++j){
                if(grid[i][j] == '1'){
                    bfs(grid, i, j);
                    result++;
                }
            }
        }

        return result;
    }

    /**
     * 根据[row，col]寻找连通的岛屿，并把它们从1置为0
     * @param row
     * @param col
     */
    public void bfs(char[][] grid, int row, int col){
        Queue<Coordinate> queue = new LinkedList<>();
        grid[row][col] = '0';
        queue.add(new Coordinate(row, col));
        while(!queue.isEmpty()){
            Coordinate cur = queue.poll();
            for(int i = 0; i < 4; ++i){
                if(isValidPos(grid.length, grid[0].length, cur.x, cur.y, directionX[i], directionY[i]) && 
                   grid[cur.x + directionX[i]][cur.y + directionY[i]] == '1'){
                    // 先变为0再放入
                    grid[cur.x + directionX[i]][cur.y + directionY[i]] = '0';
                    queue.add(new Coordinate(cur.x + directionX[i], cur.y + directionY[i]));
                }
            }
        }
    }

    boolean isValidPos(int row, int col, int x, int y, int dx, int dy){
        return (x + dx >= 0) && (x + dx < row) && (y + dy >= 0) && (y + dy < col);
    }
}
