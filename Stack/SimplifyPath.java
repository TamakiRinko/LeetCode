package Stack;

import java.util.Stack;

/**
 * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 请注意，返回的 规范路径 必须遵循下述格式：
        始终以斜杠 '/' 开头。
        两个目录名之间必须只有一个斜杠 '/' 。
        最后一个目录名（如果存在）不能 以 '/' 结尾。
        此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 */
public class SimplifyPath {
    /**
     * 按照"/"切分，依次栈，遇到 "" 和 "." 不变，遇到 ".." 出栈，其余入栈
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        // 初始化为根目录
        StringBuilder builder = new StringBuilder("/");

        String[] array = path.split("/");
        Stack<String> stack = new Stack<>();
        int n = array.length;

        if(n == 0)  return "/";

        for(int i = 0; i < n; ++i){
            if(array[i].equals(".") || array[i].equals("")){

            }else if(array[i].equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(array[i]);
            }
        }

        // 构造结果，从头部插入
        if(stack.size() == 0)   return "/";
        while(stack.size() > 1){
            builder.insert(1, stack.pop());
            builder.insert(1, "/");
        }
        builder.insert(1, stack.pop());

        return builder.toString();
    }
}
