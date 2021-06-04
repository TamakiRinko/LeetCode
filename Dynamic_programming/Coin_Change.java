package Dynamic_programming;

/**
 * Coin_Change
 */
public class Coin_Change {

    public int coinChange(int[] A, int M) {
        int[] f = new int[M + 1];
        f[0] = 0;
        for(int i = 1; i <= M; ++i){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < A.length; ++j){
                // 注意第二个条件，f[i - A[j]]可能拼不出，已经为Max了
                if(i - A[j] >= 0 && f[i - A[j]] != Integer.MAX_VALUE){
                    // f[i] = min{f[i - A[0]] + 1, f[i - A[1]] + 1...f[i - A[n - 1]] + 1}
                    min = Math.min(min, f[i - A[j]] + 1);
                }
            }
            f[i] = min;
        }
        if(f[M] == Integer.MAX_VALUE){
            f[M] = -1;
        }
        return f[M];
    }
}