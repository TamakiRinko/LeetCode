package Stack;

import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LargestRectangle {
    /**
     * 单调栈，站内单调递增！
     * @param height
     * @return
     */
    public int largestRectangle(int[] height){
        Stack<Integer> stack = new Stack<>();
        if(height == null || height.length == 0){
            return 0;
        }
        int max = -1;
        
        for(int i = 0; i <= height.length; ++i){
            int curHeight = (i == height.length) ? -1 : height[i];
            // 注意>=，说明[1, 5, 5, 5, 2]的三个5中，前面两个5的rightIndex错误，三个5的leftIndex都正确，所以只有最后一个5计算正确
            // 若是>，则只有第一个5计算正确
            while(!stack.isEmpty() && height[stack.peek()] >= curHeight){
                int width = height[stack.pop()];
                int leftIndex = -1;                     // 最左边下标为-1
                if(!stack.isEmpty()){                   // 左边还有
                    leftIndex = stack.peek();
                }
                max = Math.max(max, (i - leftIndex - 1) * width);
            }
            stack.push(i);
        }

        return max;
    }
}
