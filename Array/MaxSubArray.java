package Array;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {
    /**
     * 依次累加，若产生负数，则该部分一定不会成为最大连续子数组的前缀，若仍然为正数，则有可能
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        if(nums.length == 1)    return nums[0];
        int n = nums.length;
        int result = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0; i < n; ++i){
            cur += nums[i];
            result = Math.max(result, cur);
            if(cur < 0){
                // 跳过负数前缀
                cur = 0;
            }
        }
        return result;
    }

    /**
     * 动态规划，f[i]表示以i开始的最大连续子序列的和
     * f[i] = Math.max(nums[i], f[i + 1] + nums[i])
     * @param nums
     * @return
     */
    public int maxSubArrayDP(int[] nums) {
        if(nums == null || nums.length == 0)    return 0;
        if(nums.length == 1)    return nums[0];
        int n = nums.length;
        int[] f = new int[n];
        f[n - 1] = nums[n - 1];
        int result = f[n - 1];
        for(int i = n - 2; i >= 0; --i){
            if(f[i + 1] > 0){
                f[i] = f[i + 1] + nums[i];
            }else{
                f[i] = nums[i];
            }
            result = Math.max(result, f[i]);
        }
        return result;
    }
}
