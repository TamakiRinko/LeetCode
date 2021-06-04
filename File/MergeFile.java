package File;

import java.io.*;

public class MergeFile {
    public static void main(String[] args) {
        try(BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream("File/a.txt")));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream("File/b.txt")));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("File/c.txt")))){
            String s1, s2;
            StringBuilder sb1 = new StringBuilder(); // 存储从 a.txt 读取的单词
            StringBuilder sb2 = new StringBuilder(); // 存储从 b.txt 读取的单词
            // 读取 a.txt 中的单词
            while ((s1 = br1.readLine()) != null) {
                sb1.append(s1).append(" "); // 以空格 隔开
            }
            // 读取 .txt 中的单词
            while ((s2 = br2.readLine()) != null) {
                sb2.append(s2).append(" "); // 以空格 隔开
            }
            // 分别用空格切成 单词数组
            String[] ss1 = sb1.toString().split("\\s");
            String[] ss2 = sb2.toString().split("\\s");
            // 判断 单词数组的长度（取短的）
            int length = Math.min(ss1.length, ss2.length);
            // 交替添加 并以空格隔开
            // 先添加相等长度的单词（短单词数组的长度）
            StringBuilder s = new StringBuilder();
            for(int i = 0; i < length; i++) {
                s.append(ss1[i]).append(" ").append(ss2[i]);
                if (i < length -1) {
                    s.append(" ");
                }
            }
            // 再添加另一个单词数组剩余的单词
            // 判断哪个 单词数组 有剩余
            if (length == ss1.length) {
                for(int i = length; i < ss2.length; i++) {
                    s.append(" ").append(ss2[i]);
                }
            }else{
                for(int i = length; i < ss1.length; i++) {
                    s.append(" ").append(ss1[i]);
                }
            }
            // 写入 c.txt 文件中
            bw.write(s.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
