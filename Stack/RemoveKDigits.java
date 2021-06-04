package Stack;

import java.util.Stack;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小
 * 注意:
        num 的长度小于 10002 且 ≥ k。
        num 不会包含任何前导零。
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 */
public class RemoveKDigits {
    /**
     * 递归完成，寻找前k+1个中的最小值，去除它前面的，使它成为开头，向后继续递归
     * @param num
     * @param k
     * @return
     */
    public String removeKDigits(String num, int k) {
        if(num == null || num.length() == 0 || k <= 0)  return num;
        String result = help(num, k);
        if(!result.equals("")){
            int index = 0;
            // index < result.length()别忘了！！
            while(index < result.length() && result.charAt(index) == '0'){
                index++;
            }
            result = result.substring(index);
        }
        if(result.equals("")){
            return "0";
        }
        return result;
    }

    public String help(String num, int k){
        if(k == 0){
            return num;
        }else if(num.length() == k){
            // 全部删除
            return "";
        }

        // 找到前k+1个数中最小的，删除它前面的
        int index = 0;
        char min = '9';
        for(int i = 0; i < k + 1; ++i){
            if(num.charAt(i) < min){
                min = num.charAt(i);
                index = i;
            }
        }
        // 递归求其后面的
        String later = help(num.substring(index + 1), k - index);
        return num.substring(index, index + 1) + later;
    }

    /**
     * 贪心+单调栈，维持栈内递增即可！
     * 其实前一个方法中的找前k+1个的最小值，去除其前面的，就是单调栈可以完成的
     * 到达该最小值时，前面的都会被单调栈弹出！
     * @param num
     * @param k
     * @return
     */
    public String removeKDigitsStack(String num, int k) {
        if(num == null || num.length() == 0 || k <= 0)  return num;
        String result = "";
        Stack<Character> stack = new Stack<>();
        int n = num.length();
        for(int i = 0; i < n; ++i){
            char cur = num.charAt(i);
            while(k > 0 && !stack.isEmpty() && cur < stack.peek()){
                stack.pop();
                k--;
            }
            stack.push(cur);
        }
        while(k > 0){
            stack.pop();
            k--;
        }
        StringBuilder sBuilder = new StringBuilder("");
        while(!stack.isEmpty()){
            sBuilder.append(stack.pop());
        }
        result = sBuilder.reverse().toString();
        if(!result.equals("")){
            int index = 0;
            // index < result.length()别忘了！！
            while(index < result.length() && result.charAt(index) == '0'){
                index++;
            }
            result = result.substring(index);
        }
        if(result.equals("")){
            return "0";
        }
        return result;
    }
}
