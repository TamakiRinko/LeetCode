package String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 */
public class StringPermutation {
    /**
     * 字符串全排列问题，注意重复元素
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        ArrayList<String> result = new ArrayList<>();
        boolean[] visited = new boolean[n];
        char[] temp = new char[n];
        dfs(arr, visited, result, temp, 0);
        String[] res = result.stream().toArray(String[]::new);
        // 这样也可以！
        // String[] res2 = result.toArray(new String[result.size()]);
        return res;
    }

    public void dfs(char[] arr, boolean[] visited, ArrayList<String> result, char[] temp, int index){
        if(index == arr.length){
            result.add(new String(temp));
            return;
        }

        for(int i = 0; i < arr.length; ++i){
            // visited[i - 1] == false! 前一个相同的还没访问，就想访问我，不可以
            if(visited[i] == true || (i > 0 && visited[i - 1] == false && arr[i] == arr[i - 1])){
                continue;
            }
            visited[i] = true;
            temp[index] = arr[i];
            dfs(arr, visited, result, temp, index + 1);
            visited[i] = false;
        }
    }

    List<String> res = new LinkedList<>();
    char[] c;
    /**
     * 第二种思路，固定每一位的值，去重的策略为：保证 “每种字符只在此位固定一次” 
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        // 每次递归中新建set
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            // 该位置的值不可以重复
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}
