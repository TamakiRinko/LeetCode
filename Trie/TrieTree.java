package Trie;

import java.util.HashMap;

class TrieNode{
    char c;
    HashMap<Character, TrieNode> children = new HashMap<>();
    // 结尾处填上word，可以判断是否到了结尾
    String word;
    public TrieNode(){
        word = null;
    }
    public TrieNode(char c){
        this.c = c;
        word = null;
    }
}

public class TrieTree {
    public TrieNode root;

    public TrieTree(){
        root = new TrieNode();
    }

    // 插入一个word到TrieTree中
    public void insert(String word){
        TrieNode cur = root;
        for(int i = 0; i < word.length(); ++i){
            char c = word.charAt(i);
            if(!cur.children.containsKey(c)){
                cur.children.put(c, new TrieNode(c));
            }
            cur = cur.children.get(c);
        }
        cur.word = word;
    }

    // 查询是否有word
    public boolean search(String word){
        TrieNode node = searchWordNodePos(word);
        if(node == null || node.word == null){
            return false;
        }
        return true;
    }

    // 查询是否有word前缀字符串
    public boolean startWith(String prefix){
        TrieNode node = searchWordNodePos(prefix);
        if(node == null){
            return false;
        }
        return true;
    }

    // 查询是否有s或以s为前缀字符串的对应s末尾位置
    private TrieNode searchWordNodePos(String s){
        TrieNode cur = root;
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            if(cur.children.containsKey(c)){
                cur = cur.children.get(c);
            }else{
                return null;
            }
        }
        return cur;
    }
}
