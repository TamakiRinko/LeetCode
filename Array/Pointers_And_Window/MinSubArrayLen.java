package Array.Pointers_And_Window;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0。
 */
public class MinSubArrayLen {
    /**
     *  分治法，左，右或中间连续
     *  **错误方法**！归并时无法O(n)得出！
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_Merge(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        return helper_Merge(s, nums, 0, nums.length - 1);
    }

    public int helper_Merge(int s, int[] nums, int left, int right){
        if(right < left)    return 0;
        if(right == left){
            if(nums[left] >= s){
                return 1;
            }else{
                return 0;
            }
        }
        int mid = left + (right - left) / 2;
        int leftMin = helper_Merge(s, nums, left, mid);
        int rightMin = helper_Merge(s, nums, mid + 1, right);
        int midMin = 0;
        int midLeft = mid;
        int midRight = mid + 1;
        int sum = 0;
        while(sum < s && (midLeft >= left || midRight <= right)){
            int l = (midLeft >= left) ? nums[midLeft] : 0;
            int r = (midRight <= right) ? nums[midRight] : 0;
            if(l > r){
                sum += l;
                midLeft--;
            }else{
                sum += r;
                midRight++;
            }
            midMin++;
        }
        // 整体<s，左右都一定小于s
        if(sum < s){
            return 0;
        }
        int min = midMin;
        if(leftMin != 0){
            min = (min < leftMin) ? min : leftMin;
        }
        if(rightMin != 0){
            min = (min < rightMin) ? min : rightMin;
        }
        return min;
    }

    /**
     * 双指针+滑动窗口
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_Window(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int begin = 0, end = 0;
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        int curSum = 0;
        while(end < length){
            if(curSum < s){
                curSum += nums[end];
                end++;
            }else{
                min = Math.min(min, (end - begin));
                curSum -= nums[begin];
                begin++;
            }
        }
        // end超出后的扫尾工作
        while(begin <= end){
            if(curSum < s){
                break;
            }else{
                min = Math.min(min, (end - begin));
                curSum -= nums[begin];
                begin++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 暴力法的优化
     * 枚举起始位置，判断结尾位置时采用二分法（由于前缀和递增），找到第一个大于等于s的前缀子数组，O(nlogn)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_PrefixSum(int s, int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        // prefix长度为length + 1，prefix[i]表示前i个(数组为0~i-1)，prefix[0] = 0
        int[] prefix = new int[length + 1];
        prefix[0] = 0;
        for(int i = 1; i <= length; ++i){
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }

        // 手写二分法！
        // for(int i = 0; i < length; ++i){
        //     // i, begin和end为nums下标
        //     int begin = i;
        //     int end = length - 1;

        //     while(begin <= end){
        //         int mid = (begin + end) / 2;
        //         // nums下标转换为prefix下标时+1
        //         // 相当于数组从第i个到第mid个的和
        //         if(prefix[mid + 1] - prefix[i] >= s){
        //             min = Math.min(min, mid - i + 1);
        //             end = mid - 1;
        //         }else{
        //             begin = mid + 1;
        //         }
        //     }
        // }

        // 使用Arrays自带的二分查找
        for (int i = 1; i <= length; i++) {
            int target = s + prefix[i - 1];
            // 从prefix中找target，如果找不到，则返回(-插入点 - 1)，插入点即第一个大于target的点
            int bound = Arrays.binarySearch(prefix, target);
            if (bound < 0) {
                // 没找到等于target的点，-bound-1为插入点，即当前位置的值大于target，也可能超出prefix的长度
                bound = -bound - 1;
            }
            if (bound <= length) {
                // 没超出prefix的长度，即存在大于等于target的位置
                min = Math.min(min, bound - (i - 1));
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
