package DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */
public class GenerateParenthesis {
    /**
     * DFS，当左括号使用的数量大于右括号时，下一步可以选择左括号或右括号
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if(n == 0)  return new ArrayList<>();
        List<String> result = new ArrayList<>();
        dfs(n, n, "", result);
        return result;
    }

    public void dfs(int leftNum, int rightNum, String cur, List<String> result){
        if(leftNum < 0 || rightNum < 0) return;
        if(leftNum == 0 && rightNum == 0){
            result.add(cur);
        }
        if(leftNum == rightNum){
            dfs(leftNum - 1, rightNum, cur + '(', result);
        }else if(leftNum < rightNum){
            dfs(leftNum - 1, rightNum, cur + '(', result);
            dfs(leftNum, rightNum - 1, cur + ')', result);
        }
    }
}
