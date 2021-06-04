package String;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }
        int num = strs.length;
        StringBuilder stringBuilder = new StringBuilder("");
        int index = 0;
        while(true){
            if(index >= strs[0].length())   return stringBuilder.toString();
            char s = strs[0].charAt(index);
            for(int i = 1; i < num; ++i){
                if(index >= strs[i].length() || strs[i].charAt(index) != s){
                    return stringBuilder.toString();
                }
            }
            stringBuilder.append(s);
            index++;
        }
    }
}
