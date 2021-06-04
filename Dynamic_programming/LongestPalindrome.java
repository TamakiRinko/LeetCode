package Dynamic_programming;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class LongestPalindrome {
    /**
     * 动态规划:f[i][j] = f[i + 1][j - 1] && s[i] == s[j]
     * 长度为1和2的单独计算
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        // 长度为1
        for(int i = 0; i < n; ++i){
            f[i][i] = true;
        }
        // 长度为2
        for(int i = 0; i < n - 1; ++i){
            f[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        // 长度大于2
        for(int i = n - 2; i >= 0; --i){
            for(int j = i + 2; j < n; ++j){
                f[i][j] = (f[i + 1][j - 1] && s.charAt(i) == s.charAt(j));
            }
        }

        int max = 0;
        String result = "";
        for(int i = 0; i < n; ++i){
            for(int j = i; j < n; ++j){
                if(f[i][j] == true && max < j - i + 1){
                    max = j - i + 1;
                    result = s.substring(i, j + 1);
                }
            }
        }

        return result;
    }
}
