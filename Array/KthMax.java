package Array;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 */
public class KthMax {

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3,2,3,1,2,4,5,5,6}, 4));
    }

    /**
     * 利用partition操作
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        return partition(nums, 0, nums.length - 1, k);
    }

    public static int partition(int[] nums, int begin, int end, int m){
        if(begin == end) return nums[begin];
        // 随机化pivot！
        int rand = new Random().nextInt(end - begin + 1) + begin;
        swap(nums, begin, rand);
        int pivot = nums[begin];
        int pivotIndex = begin + 1;
        for(int i = begin + 1; i <= end; ++i){
            if(nums[i] > pivot){
                swap(nums, pivotIndex, i);
                pivotIndex++;
            }
        }
        swap(nums, begin, pivotIndex - 1);
        int index = pivotIndex - 1;
        // index - begin！看看前面有多少个
        if(index - begin == m - 1){
            return nums[index];
        }else if(index - begin > m - 1){
            return partition(nums, begin, index - 1, m);
        }else{
            return partition(nums, index + 1, end, m - (index - begin + 1));
        }
    }

    public static void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 利用最大堆
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestHeap(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b)->{return b - a;});
        for(int i = 0; i < n; ++i){
            pq.add(nums[i]);
        }
        for(int i = 0; i < k - 1; ++i){
            pq.poll();
        }
        return pq.poll();
    }

    /**
     * 直接排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargestSort(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }
}
