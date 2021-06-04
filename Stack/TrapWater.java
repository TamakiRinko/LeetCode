package Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class TrapWater {
    /**
     * 找每个位置的左右最大值中的较小值，差作为其最终接水高度
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int result = 0;
        if(height == null || height.length == 0){
            return 0;
        }
        int n = height.length;
        int max = -1;
        int[] left = new int[n];
        int[] right = new int[n];
        for(int i = 0; i < n; ++i){
            left[i] = max;
            if(height[i] > max){
                max = height[i];
            }
        }
        max = -1;
        for(int i = n - 1; i >= 0; --i){
            right[i] = max;
            if(height[i] > max){
                max = height[i];
            }
        }
        for(int i = 0; i < n; ++i){
            int tall = Math.min(left[i], right[i]);
            if(height[i] < tall){
                result += (tall - height[i]);
            }
        }
        return result;
    }
}
