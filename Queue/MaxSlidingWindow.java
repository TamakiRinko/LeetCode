package Queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 */
public class MaxSlidingWindow {
    /**
     * 单调队列！
     * 使用双端队列作为窗口，队首为当前队列最大值*下标*，依次往队尾越来越小！
     * 每次加入新元素，判断新元素与之前元素的大小关系，比之前大的，之前的不可能再成为窗口内的最大元素
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k > nums.length) return null;
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;

        // 双端队列，存储下标
        Deque<Integer> deque = new ArrayDeque<>();
        
        for(int i = 0; i < n; ++i){
            // deque.peekFirst() == i - k 表示窗口即将越过当前的最大值，要把当前最大值删去！
            // 不可以使用deque.size() == k 来判断！！
            if (i >= k && !deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.removeFirst();
            }
            int cur = nums[i];
            // 单调队列，小于cur的全部弹出
            while(!deque.isEmpty() && nums[deque.getLast()] < cur){
                deque.removeLast();
            }
            deque.addLast(i);
            if(i >= k - 1){
                result[index] = nums[deque.getFirst()];
                index++;
            }
        }

        // while(index < n + k - 1){
        //     while(deque.getFirst() + k < index){
        //         deque.removeFirst();
        //     }
        //     result[index] = nums[deque.getFirst()];
        //     index++;
        // }

        return result;
    }
}
