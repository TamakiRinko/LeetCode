package Array.Pointers_And_Window;

import java.util.LinkedList;
import java.util.Queue;

public class LongestOnes {
    public int longestOnes(int[] A, int K) {
        int i = 0, j = 0;
        int zeros = 0;
        int ans = 0;
    
        while(j < A.length) {
            if(A[j] == 0) {
                zeros++;
                // 不使用Queue，用while循环找第一个0变为1的位置
                while (zeros > K) {
                    if(A[i] == 0) {
                        zeros--;
                    }
                    i++;
                }
            }
            j++;
            ans = Math.max(ans, j - i);
        }
        return ans;
    }
    
    /**
     * 使用Queue保存0变为1的下标
     * @param A
     * @param K
     * @return
     */
    public int longestOnes_Queue(int[] A, int K) {
        if(A == null || A.length == 0){
            return 0;
        }
        Queue<Integer> queue = new LinkedList<>();
        int n = A.length;
        int begin = 0, end = 0;
        int maxNum = 0;
        while(end < n){
            if(A[end] == 1){
                end++;
            }else{
                if(queue.size() < K){
                    // end从0变为1
                    queue.offer(end);
                    end++;
                }else{
                    // 使用了K个了，把第一个使用的变为0，当前end变为1
                    maxNum = Math.max(maxNum, end - begin);
                    if(K == 0){
                        begin = end + 1;
                    }else{
                        begin = queue.poll() + 1;
                        queue.offer(end);
                    }
                    end++;
                }
            }
        }
        maxNum = Math.max(maxNum, end - begin);
        return maxNum;
    }
}
