package BFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        if(dict == null || dict.size() == 0)    return 0;

        int length = 1;
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        queue.offer(start);
        set.add(start);

        while(!queue.isEmpty()){
            length++;
            int size = queue.size();
            // 每次判断一层全部的可能性
            for(int i = 0; i < size; ++i){
                String cur = queue.poll();
                for(String nextWord: getNextWord(dict, cur, end)){
                    if(set.contains(nextWord)){
                        continue;
                    }
                    if(nextWord.equals(end)){
                        return length;
                    }
                    queue.offer(nextWord);
                    set.add(nextWord);
                }
            }
        }

        return 0;
    }

    /**
     * O(26 * L^2)，L为单词长度，与字典长度无关
     * 不要寻找字典里每个单词是否可能，二十判断每种可能变换是否在字典中
     * @param dict
     * @param word
     * @return
     */
    ArrayList<String> getNextWord(Set<String> dict, String word, String end){
        ArrayList<String> nextWords = new ArrayList<>();
        for(int i = 0; i < word.length(); ++i){
            for(char j = 'a'; j <= 'z'; ++j){
                if(j == word.charAt(i)) continue;
                String nextWord = replace(word, i, j);
                if(nextWord.equals(end)){
                    nextWords.add(nextWord);
                    return nextWords;
                }
                if(dict.contains(nextWord)){
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }

    private String replace(String word, int i, char j) {
        char[] array = word.toCharArray();
        array[i] = j;
        return new String(array);
    }
}
