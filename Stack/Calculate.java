package Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 简易计算器，s 由数字、'+'、'-'、'('、')'、和 ' ' 组成，且s有效
 */
public class Calculate {
    /**
     * 符号栈和数字栈，需要注意的是，符号栈是一个单调栈！栈内单调递增
     * @param s
     * @return
     */
    public static int calculate(String s) {
        s = s.replace(" ", "");
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> symStack = new Stack<>();
        // 优先级顺序
        Map<Character, Integer> seq = new HashMap<>();
        seq.put('(', 2);
        seq.put('+', 1);
        seq.put('-', 1);
        // split函数，正则表达式，+，(，)都需要转义。每两个分隔符之间即使为空，也会算上
        // 开头若是分隔符，第一个元素为空，结尾则不会
        // String[] numbers = s.split("\\+|-|\\(|\\)");
        // for(String number: numbers){
        //     System.out.println(number);
        // }
        int n = s.length();
        for(int i = 0; i < n; ++i){
            if(s.charAt(i) <= '9' && s.charAt(i) >= '0'){
                String sNum = getNumber(s, i);
                i += (sNum.length() - 1);
                int number = Integer.parseInt(sNum);
                numStack.push(number);
            }else if(s.charAt(i) == ')'){
                char symbol;
                while((symbol = symStack.pop()) != '('){
                    int num2 = numStack.pop();
                    // numStack.isEmpty的判断是为了防止负数，若为负数-x，则当作0-x计算，第一个操作数为0
                    int num1 = numStack.isEmpty() ? 0 : numStack.pop();
                    numStack.push(cal(num1, num2, symbol));
                }
            }else{
                char symbol = s.charAt(i);
                if(symStack.isEmpty() || seq.get(symbol) > seq.get(symStack.peek())){
                    symStack.push(symbol);
                }else{
                    while(!symStack.isEmpty() && symStack.peek() != '(' && seq.get(symbol) <= seq.get(symStack.peek())){
                        char symbol2 = symStack.pop();
                        int num2 = numStack.pop();
                        int num1 = numStack.isEmpty() ? 0 : numStack.pop();
                        numStack.push(cal(num1, num2, symbol2));
                    }
                    symStack.push(symbol);
                }
            }
        }
        while(!symStack.isEmpty()){
            char sym = symStack.pop();
            int num2 = numStack.pop();
            int num1 = numStack.isEmpty() ? 0 : numStack.pop();
            numStack.push(cal(num1, num2, sym));
        }
        return numStack.peek();
    }
    
    private static int cal(int num1, int num2, char sym) {
        switch(sym){
            case '+': return num1 + num2;
            case '-': return num1 - num2;
            default: return 0;
        }
    }

    private static String getNumber(String s, int i) {
        int index = i + 1;
        while(index < s.length() && s.charAt(index) <= '9' && s.charAt(index) >= '0'){
            index++;
        }
        return s.substring(i, index);
    }



    public static void main(String[] args) {
        System.out.println(calculate("2+(-1)"));
    }
}
