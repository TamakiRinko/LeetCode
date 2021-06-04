package Binary_Search;

public class Search_Insert_Position {
    public int searchInsert(int[] A, int target){
        if(A == null || A.length == 0){
            return 0;
        }
        int start = 0, end = A.length - 1;
        // 出循环时start + 1 == end
        while(start + 1 < end){
            int mid = start + (end - start) / 2;
            if(A[mid] > target){
                end = mid;
            }else if(A[mid] == target){
                end = mid;
            }else{
                start = mid;
            }
        }
        
        // 先start，因为是找第一个 >= start的位置
        if(A[start] >= target){
            return start;
        }
        if(A[end] >= target){
            return end;
        }
        // 末尾
        return A.length;
    }
}
