package Array.Pointers_And_Window;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 */
public class ThreeSumClosest {
    /**
     * 双指针法
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        // a + b + c，返回值
        int result = Integer.MAX_VALUE;
        // Math.abs(a + b + c - target)，比较使用
        int dis = Integer.MAX_VALUE;
        if(nums == null || nums.length < 3) return result;

        Arrays.sort(nums);

        int n = nums.length;
        for(int i = 0; i < n - 2; ++i){
            int a = nums[i];
            int begin = i + 1;
            int end = n - 1;
            while(begin < end){
                int b = nums[begin];
                int c = nums[end];
                if(a + b + c == target){
                    return target;
                }else if(a + b + c < target){
                    int newDis = target - a - b - c;
                    result = newDis < dis ? (a + b + c) : result;
                    dis = newDis < dis ? newDis : dis;
                    begin++;
                }else{
                    int newDis = a + b + c - target;
                    result = newDis < dis ? (a + b + c) : result;
                    dis = newDis < dis ? newDis : dis;
                    end--;
                }
            }
        }

        return result;
    }
}
