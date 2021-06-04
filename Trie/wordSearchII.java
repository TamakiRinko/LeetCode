package Trie;

import java.util.ArrayList;
import java.util.List;

public class wordSearchII {

    // 前进方向
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};

    public void searchII(char[][] board, int x, int y, TrieNode root, List<String> results){
        if(!root.children.containsKey(board[x][y])){
            return;
        }
        TrieNode child = root.children.get(board[x][y]);

        // 找到一条正确路径
        if(child.word != null){
            if(!results.contains(child.word)){
                results.add(child.word);
            }
        }

        char c = board[x][y];
        // 已使用过
        board[x][y] = 0;
        for(int i = 0; i < 4; ++i){
            if(isValid(board, x + dx[i], y + dy[i])){
                searchII(board, x + dx[i], y + dy[i], child, results);
            }
        }
        // 恢复board
        board[x][y] = c;
        return;
    }

    public List<String> wordSearch(char[][] board, List<String> words) {
        List<String> results = new ArrayList<>();
        // write your code here
        TrieTree tree = new TrieTree();
        for(String word:words){
            tree.insert(word);
        }
        for(int i = 0; i < board.length; ++i){
            for(int j = 0; j < board[0].length; ++j){
                searchII(board, i, j, tree.root, results);
            }
        }
        return results;
    }

    private boolean isValid(char[][] board, int x, int y){
        if(x < 0 || x >= board.length || y < 0 || y >= board[0].length){
            return false;
        }
        return board[x][y] != 0;
    }
}
