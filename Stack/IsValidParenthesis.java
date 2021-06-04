package Stack;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 */
public class IsValidParenthesis {
    /**
     * 用栈即可，注意结束栈不为空，返回false
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s == null || s.length() == 0)    return true;
        Stack<Character> stack = new Stack<>();
        int n = s.length();
        for(int i = 0; i < n; ++i){
            if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{'){
                stack.push(s.charAt(i));
            }else if(s.charAt(i) == ')'){
                if(stack.isEmpty() || stack.pop() != '('){
                    return false;
                }
            }else if(s.charAt(i) == ']'){
                if(stack.isEmpty() || stack.pop() != '['){
                    return false;
                }
            }else if(s.charAt(i) == '}'){
                if(stack.isEmpty() || stack.pop() != '{'){
                    return false;
                }
            }
        }
        if(!stack.isEmpty())    return false;
        return true;
    }
}
