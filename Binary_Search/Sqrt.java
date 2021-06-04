package Binary_Search;

public class Sqrt {
    public int solution(int x){
        long start = 0;
        long end = x;
        // 不用在这儿计算mid，后面循环判断只要start和mid
        // int mid = start + (end - start) / 2;
        while(start + 1 < end){
            long mid = start + (end - start) / 2;
            if(mid * mid < x){
                start = mid;
            }else if(mid * mid == x){
                start = mid;
            }else{
                end = mid;
            }
        }
        
        // 与mid无关了，只剩start和end两个位置没有考虑
        if(end * end <= x){
            return (int)end;
        }
        return (int)start;
    }
}
